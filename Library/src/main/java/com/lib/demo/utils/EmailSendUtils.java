package com.lib.demo.utils;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.lib.demo.bean.Book;
import com.lib.demo.bean.User;

/**
 * @邮箱发送工具类
 * @author Administrator @version1.0.0
 */
@Component
public class EmailSendUtils {

	@Autowired
	JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String mailFrom;

//	private static SimpleMailMessage message = new SimpleMailMessage();

	private static Date date = new Date(System.currentTimeMillis());

	public void sendEmail(User user, Book book, Integer sign) {
		Integer id = user.getId();
		String username = user.getName();
		String mailTo = user.getEmail();
		String bookname = book.getName();
		String text = new String();
		String subject = new String();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailFrom);
		message.setTo(mailTo);

		switch (sign) {
		case 0:
			subject = "借书成功";
			text = String.format("尊敬的用户：%s(id：%d)，管理员已审批您的借书申请，" + "书籍名为：%s。%n%tF", username, id, bookname, date);
			break;
		case 1:
			subject = "借书失败";
			text = String.format("尊敬的用户：%s(id：%d)，您的借书数量已达上限，" + "请还书后再试。----------%n%tF", username, id, date);
			break;
		case 2:
			subject = "借书失败";
			text = String.format("尊敬的用户：%s(id：%d)，您已借阅了这本书(书籍名：《%s>)，" + "请归还此书后再试。-----------%n%tF", username, id, bookname, date);
			break;
		case 3:
			subject = "借书失败";
			text = String.format("尊敬的用户：%s(id：%d)，您所借的书籍书库存量不足(书籍名：《%s》)，" + "请稍后再试。-----------%n%tF", username, id, bookname, date);
			break;
		case 4:
			subject = "借书失败";
			text = String.format("尊敬的用户：%s(id：%d)，您的借书数量已达上限，" + "请还书后稍后再试。----------%n%tF", username, id, date);
			break;
		default:
			subject = "借书失败";
			text = String.format("尊敬的用户：%s(id：%d)，管理员已拒绝你的借书申请(书籍名：《%s》)，" + "请稍后再试。--------%n%tF", username, id, bookname, date);
			break;
		}
		System.out.println(mailTo);
		System.out.println(mailFrom);
		message.setSubject(subject);
		message.setText(text);
		try {
			mailSender.send(message);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public boolean sendEmail(User user, Book book) {
		if (user == null || book == null) {
			return false;
		}
		Integer id = user.getId();
		String username = user.getName();
		String mailTo = user.getEmail();
		String bookname = book.getName();

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailFrom);
		message.setTo(mailTo);

		String subject = new String("提醒邮件");
		String text = String.format("尊敬的用户%s(id：%d)，你所借的书籍：%s即将到期，" + "请及时归还图书，谢谢合作。%n%tF", username, id, bookname,
				date);

		message.setSubject(subject);
		message.setText(text);

		try {
			mailSender.send(message);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
