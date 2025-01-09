package com.example.be.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.be.dto.request.RegisterUserRequest;
import com.example.be.model.Account;
import com.example.be.repository.AccountRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    public int existByEmailConfirmation(String email) {
        Optional<Account> optionalAccount = accountRepository.findByEmail(email);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            boolean confir = account.isConfirmation();
            if (confir) {
                return 2;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public void sendCustomEmail(RegisterUserRequest registerUserRequest, String subject, String confirmationUrl)
            throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(registerUserRequest.getEmail());
        helper.setSubject(subject);

        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Xác nhận Email</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 20px;\n" +
                "            background-color: #ffffff;\n" +
                "            border-radius: 5px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "\n" +
                "        h2 {\n" +
                "            color: #333333;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            color: #666666;\n" +
                "            line-height: 1.5;\n" +
                "        }\n" +
                "\n" +
                "        a {\n" +
                "            color: #007bff;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "\n" +
                "        a:hover {\n" +
                "            text-decoration: underline;\n" +
                "        }\n" +
                "\n" +
                "        .footer {\n" +
                "            margin-top: 20px;\n" +
                "            font-size: 12px;\n" +
                "            color: #999999;\n" +
                "        }\n" +
                "\n" +
                "        .logo {\n" +
                "            display: block;\n" +
                "            margin: 0 auto;\n" +
                "            max-width: 200px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "    <h2>Xác minh tài khoản</h2>\n" +
                "    <p>Xin chào <strong>{{username}}</strong>,</p>\n" +
                "    <p>Vui lòng nhấn vào đường dẫn dưới đây để xác minh địa chỉ email của bạn:</p>\n" +
                "    <p><a href=\"{{confirmationUrl}}\">Xác nhận Email</a></p>\n" +
                "    <p>Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này.</p>\n" +
                "    <p class=\"footer\">Trân trọng,<br>Đội ngũ hỗ trợ của chúng tôi</p>\n" +
                "</div>\n" +
                "</body>\n" +
                "\n" +
                "</html>\n";

        htmlContent = htmlContent.replace("{{username}}", registerUserRequest.getUsername());
        htmlContent = htmlContent.replace("{{confirmationUrl}}", confirmationUrl);

        helper.setText(htmlContent, true);
        javaMailSender.send(message);
    }
}
