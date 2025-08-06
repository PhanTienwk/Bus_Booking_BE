package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.dto.response.BusTripSearchResponse;
import com.thuctap.busbooking.dto.response.CostSummaryResponse;
import com.thuctap.busbooking.dto.response.PassengerTripInfoResponse;
import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.repository.BusTripRepository;
import com.thuctap.busbooking.repository.InvoiceRepository;
import com.thuctap.busbooking.SpecificationQuery.FilterBusTrip;
import com.thuctap.busbooking.dto.request.BusTripFilterRequest;
import com.thuctap.busbooking.dto.request.BusTripRequest;
import com.thuctap.busbooking.entity.*;
import com.thuctap.busbooking.repository.*;
import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.BusTripService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusTripServiceImpl implements BusTripService {

    BusTripRepository busTripRepo;
    BusTypeRepository busTypeRepo;
    BusRepository busRepo;
    BusRouteRepository busRouteRepo;
    UserRepository userRepo;
    TicketRepository ticketRepository;
    SeatPositionRepository seatPositionRepository;
    InvoiceRepository invoiceRepository;


    public List<BusTrip> getAllBusTrip() {
        return busTripRepo.findAll();
    }

    public List<BusRoute> getAllBusRoutes() {
        return busRouteRepo.findAll();
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Boolean updateBusTripStatus(Integer id, Integer status) {
        BusTrip busTrip = busTripRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bến xe với id: " + id));

        if (status != 0 && status != 1) {
            throw new IllegalArgumentException("Trạng thái phải là 0 hoặc 1");
        }

        busTrip.setStatus(status);
        busTrip.setUpdatedAt(LocalDateTime.now());
        return busTripRepo.save(busTrip) != null;
    }

    public BusTrip addBusTrip(BusTripRequest request) {
        if (request.getBusRouteId() == null || request.getDepartureTime() == null ||
                request.getCostOperating() == null || request.getCostIncurred() == null ||
                request.getPrice() == null || request.getBusId() == null || request.getDriverId() == null) {
            throw new IllegalArgumentException("Vui lòng điền đầy đủ các trường bắt buộc");
        }

        // Tìm các thực thể liên quan
        BusRoute busRoute = busRouteRepo.findById(request.getBusRouteId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tuyến xe với id: " + request.getBusRouteId()));
        Bus bus = busRepo.findById(request.getBusId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy xe với id: " + request.getBusId()));
        User driver = userRepo.findById(request.getDriverId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài xế với id: " + request.getDriverId()));

        // Kiểm tra và tạo ghế cho xe nếu chưa tồn tại


        // Tạo mới BusTrip
        BusTrip busTrip = BusTrip.builder()
                .busRoute(busRoute)
                .departureTime(request.getDepartureTime())
                .costOperating(request.getCostOperating())
                .costIncurred(request.getCostIncurred())
                .price(request.getPrice())
                .bus(bus)
                .user(driver)
                .status(request.getStatus() != null ? request.getStatus() : 1)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return busTripRepo.save(busTrip);
    }


    public BusTrip updateBusTrip(Integer id, BusTripRequest request) {
        // Tìm chuyến xe cần cập nhật
        BusTrip busTrip = busTripRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyến xe với id: " + id));

        // Kiểm tra các trường bắt buộc
        if (request.getBusRouteId() == null || request.getDepartureTime() == null ||
                request.getCostOperating() == null || request.getCostIncurred() == null ||
                request.getPrice() == null || request.getBusId() == null || request.getDriverId() == null) {
            throw new IllegalArgumentException("Vui lòng điền đầy đủ các trường bắt buộc");
        }

        // Tìm các thực thể liên quan
        BusRoute busRoute = busRouteRepo.findById(request.getBusRouteId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tuyến xe với id: " + request.getBusRouteId()));
        Bus bus = busRepo.findById(request.getBusId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy xe với id: " + request.getBusId()));
        User driver = userRepo.findById(request.getDriverId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài xế với id: " + request.getDriverId()));

        // Cập nhật thông tin chuyến xe
        busTrip.setBusRoute(busRoute);
        busTrip.setDepartureTime(request.getDepartureTime());
        busTrip.setCostOperating(request.getCostOperating());
        busTrip.setCostIncurred(request.getCostIncurred());
        busTrip.setPrice(request.getPrice());
        busTrip.setBus(bus);
        busTrip.setUser(driver);
        busTrip.setStatus(request.getStatus() != null ? request.getStatus() : busTrip.getStatus());
        busTrip.setUpdatedAt(LocalDateTime.now());

        return busTripRepo.save(busTrip);
    }

    public List<BusTrip> filterBusTrips(BusTripFilterRequest request) {
        return busTripRepo.findAll(FilterBusTrip.filterBusTrip(
                request.getId(),
                request.getBusRouteId(),
                request.getDepartureTime(),
                request.getBusId(),
                request.getDriverId(),
                request.getStatus()
        ));
    }

    @Override
    public List<PassengerTripInfoResponse> getPassengerTripInfoByTripId(Integer tripId) {
        BusTrip busTrip = busTripRepo.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyến xe với id: " + tripId));

        Integer busId = busTrip.getBus().getId();
        if (busId == null) {
            throw new RuntimeException("Chuyến xe không có thông tin xe");
        }

        List<SeatPosition> seats = seatPositionRepository.findByBusId(busId);

        List<Invoice> invoices = invoiceRepository.findByBusTripId(tripId);
        if (invoices.isEmpty()) {
            return seats.stream()
                    .map(seat -> PassengerTripInfoResponse.builder()
                            .seatName(seat.getName())
                            .isBooked(false)
                            .name(null)
                            .phone(null)
                            .pickupPoint(null)
                            .dropOffPoint(null)
                            .build())
                    .toList();
        }

        List<Integer> invoiceIds = invoices.stream()
                .map(Invoice::getId)
                .toList();

        List<Ticket> tickets = ticketRepository.findByInvoiceIdIn(invoiceIds);

        Map<Integer, Ticket> seatToTicketMap = tickets.stream()
                .collect(Collectors.toMap(t -> t.getSeatPosition().getId(), Function.identity()));

        List<PassengerTripInfoResponse> result = new ArrayList<>();

        for (SeatPosition seat : seats) {
            Ticket ticket = seatToTicketMap.get(seat.getId());

            result.add(PassengerTripInfoResponse.builder()
                    .seatName(seat.getName())
                    .isBooked(ticket != null)
                    .name(ticket != null ? ticket.getInvoice().getName() : null)
                    .phone(ticket != null ? ticket.getInvoice().getPhone() : null)
                    .pickupPoint(ticket != null ? "Điểm lên xe" : null)
                    .dropOffPoint(ticket != null ? "Điểm xuống xe" : null)
                    .build());
        }
        return result;
    }

    public List<BusTripSearchResponse> getBusTrip(int startStationId, int endStationId, LocalDate date, int count) {
        LocalDateTime currentTimePlusTwoHours = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")).plusHours(2);
        List<BusTrip> list = busTripRepo.findTripsWithIntermediateStops(startStationId, endStationId,date,currentTimePlusTwoHours);
        List<BusTripSearchResponse> responseList = new ArrayList<>();
        for(BusTrip busTrip : list){
            int countA = 0;
            int countB = 0;
            List<SeatPosition> busTripList = new ArrayList<SeatPosition>();
            List<SeatPosition> seatPositionList = seatPositionRepository.findByBusId(busTrip.getBus().getId());
            for(SeatPosition seatPosition : seatPositionList){
                if(ticketRepository.existsBySeatPositionIdAndBusTripId(seatPosition.getId(),busTrip.getId())){
                    Ticket ticket = ticketRepository.findBySeatPositionIdAndBusTripId(seatPosition.getId(),busTrip.getId());
                    if(ticket.getStatus()==0 || ticket.getStatus()==4 ) busTripList.add(seatPosition);
                }else busTripList.add(seatPosition);
            }
            if(busTripList!=null){
                for(SeatPosition position:busTripList){
                    if(position.getName().contains("A")) countA++;
                    else countB++;
                }
            }
            if((countA+countB)<count) continue;
            BusTripSearchResponse busTripSearchResponse = BusTripSearchResponse.builder()
                    .id(busTrip.getId())
                    .bus(busTrip.getBus())
                    .busRoute(busTrip.getBusRoute())
                    .costIncurred(busTrip.getCostIncurred())
                    .departureTime(busTrip.getDepartureTime())
                    .costOperating(busTrip.getCostOperating())
                    .status(busTrip.getStatus())
                    .price(busTrip.getPrice())
                    .count(countA+countB)
                    .countA(countA)
                    .countB(countB)
                    .build();
            responseList.add(busTripSearchResponse);
        }
        return responseList;
    }


}
