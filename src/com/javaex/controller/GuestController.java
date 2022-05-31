package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

public class GuestController {

	// 필드
	private static final long serialVersionUID = 1L;

	// 생성자(기본생성자)
	// GS

	// 일반
	// Get방식으로 요청시 호출 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 코드작성
		System.out.println("controller");

		String action = request.getParameter("action");
		System.out.println(action);

		// 리스트
		if ("addList".equals(action)) {
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestList = guestbookDao.getGuestList();
			System.out.println(guestList);

			// request에 데이터 추가
			request.setAttribute("gList", guestList);

			// 데이터 + html
			RequestDispatcher rd = request.getRequestDispatcher("/addList.jsp");
			rd.forward(request, response);
		} else if ("add".equals(action)) {

			// 파라미터에서 값 꺼내기(name, password, content)
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");

			// Vo만들기
			GuestbookVo guestbookVo = new GuestbookVo(name, password, content);

			// guestbookDao.addguest()를 통해 저장하기
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.addGuest(guestbookVo);

			response.sendRedirect("/guestbook2/gcr?action=addList");

		} else if ("deleteForm".equals(action)) {

			RequestDispatcher rd = request.getRequestDispatcher("/deleteForm.jsp");
			rd.forward(request, response);

		} else if ("delete".equals(action)) {

			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");

			GuestbookDao guestbookDao = new GuestbookDao();
			String oraclepassword = guestbookDao.guestPassword(no);

			if (password.equals(oraclepassword)) {
				guestbookDao.guestDelete(no);
				response.sendRedirect("/guestbook2/gcr?action=addList");
			} else {
				response.sendRedirect("/guestbook2/gcr?action=addList");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
