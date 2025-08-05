package com.thuctap.busbooking.service.impl;

import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.entity.Ticket;
import com.thuctap.busbooking.service.auth.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class EmailServiceImpl implements EmailService {
    JavaMailSender mailSender;
    public void sendEmailTicket(BusTrip busTrip, List<Ticket> ticketList,String email) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setTo(email);
        helper.setSubject("Xác nhận vé xe khách - " );
        helper.setFrom("no-reply@buscompany.com");

        String htmlContent = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    body { font-family: Arial, sans-serif; color: #333; }
                    .container { max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; }
                    .header { background-color: #f8f8f8; padding: 10px; text-align: center; }
                    .content { padding: 20px; }
                    .ticket-info { border-collapse: collapse; width: 100%; }
                    .ticket-info th, .ticket-info td { border: 1px solid #ddd; padding: 8px; text-align: left; }
                    .ticket-info th { background-color: #f2f2f2; }
                    .footer { text-align: center; font-size: 12px; color: #777; margin-top: 20px; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h2>Xác Nhận Vé Xe Khách</h2>
                    </div>
                    <div class="content">
                        <p>Kính gửi %s,</p>
                        <p>Cảm ơn bạn đã đặt vé tại công ty chúng tôi. Dưới đây là thông tin chi tiết vé của bạn:</p>
                        <table class="ticket-info">
                            <tr>
                                <th>Mã vé</th>
                                <td>%s</td>
                            </tr>
                            <tr>
                                <th>Điểm đi</th>
                                <td>%s</td>
                            </tr>
                            <tr>
                                <th>Điểm đến</th>
                                <td>%s</td>
                            </tr>
                            <tr>
                                <th>Thời gian khởi hành</th>
                                <td>%s</td>
                            </tr>
                            <tr>
                                <th>Số ghế</th>
                                <td>%s</td>
                            </tr>
                        </table>
                        <p>Vui lòng có mặt tại bến xe trước giờ khởi hành ít nhất 30 phút. 
                        Mang theo mã vé hoặc giấy tờ tùy thân để check-in.</p>
                        <p>Nếu có bất kỳ thắc mắc nào, vui lòng liên hệ hotline: 1900 1234.</p>
                    </div>
                    <div class="footer">
                        <p>Công ty TNHH Vận Tải Khách</p>
                        <p>Email: support@buscompany.com | Website: www.buscompany.com</p>
                    </div>
                </div>
            </html>
            """;
//                .formatted(passengerName, ticketId, departure, destination, departureTime, seatNumber);

        helper.setText(htmlContent, true);
        mailSender.send(mimeMessage);
    }
}
