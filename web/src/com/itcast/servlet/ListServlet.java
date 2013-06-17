package com.itcast.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.domain.News;
import com.itcast.service.VideoNewsService;
import com.itcast.service.impl.VideoNewsServiceImpl;

public class ListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private VideoNewsService service=new  VideoNewsServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			List<News> videos=service.getLastNews();
			String format=request.getParameter("format");
			if ("json".equals(format)) {
				StringBuilder builder=new StringBuilder();
				builder.append('[');
				for (News news : videos) {
				builder.append("{id:'"+news.getId()+"',");
				builder.append("title:'"+news.getTitle()+"',");
				builder.append("timelength:'"+news.getTimelength()+"'},");
				}
				builder.deleteCharAt(builder.length()-1);
				builder.append(']');
				request.setAttribute("json", builder.toString());
				request.getRequestDispatcher("/WEB-INF/page/jsonvideonews.jsp").forward(request, response);
				
			}else{
			request.setAttribute("videos", videos);
			request.getRequestDispatcher("/WEB-INF/page/videonews.jsp").forward(request, response);
			}
	}

}
