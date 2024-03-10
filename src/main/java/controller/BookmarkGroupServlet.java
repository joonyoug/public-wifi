package controller;

import db.BookMarkGroupService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    북마크 이름 추가 삭제 수정


 */


@WebServlet("/BookmarkGroupServlet")
public class BookmarkGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String action=request.getParameter("action");
        if(action.equals("add")){
            addBookmark(request,response);
        }
        else if(action.equals("update")){
            updateBookmark(request,response);
        }
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String action=request.getParameter("action");
        if(action.equals("delete")){
            deleteBookmark(request,response);
        }
    }
    private void addBookmark(HttpServletRequest request,HttpServletResponse response) throws IOException {
        BookMarkGroupService bookMarkGroupService=new BookMarkGroupService();
        String name=request.getParameter("bookmarkName");
        String order_number=request.getParameter("bookmarkOrder");
        boolean success=false;
        int affected=bookMarkGroupService.addBookMark(name,order_number);
        if(affected>0){
            success=true;
        }
        response.sendRedirect("bookMarkGroupAdd.jsp?success="+success);

    }
    private void deleteBookmark(HttpServletRequest request,HttpServletResponse response) throws IOException {
        BookMarkGroupService bookMarkGroupService=new BookMarkGroupService();
        String id=request.getParameter("id");
        boolean success=false;
        int affected=bookMarkGroupService.deleteBookMark(id);

        if(affected>0){
            success=true;
        }
        response.sendRedirect("bookMarkGroup.jsp?success="+success);

    }
    private void updateBookmark(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String oldBookMarkGroupName = request.getParameter("oldBookMarkGroupName");
        String newBookMarkGroupName = request.getParameter("bookMarkGroupName");
        String newBookMarkGroupOrder = request.getParameter("bookMarkGroupOrder");

        BookMarkGroupService bookMarkGroupService=new BookMarkGroupService();
        int affected=bookMarkGroupService.updateBookMark(oldBookMarkGroupName,newBookMarkGroupName,newBookMarkGroupOrder);
        boolean success=false;
        if(affected>=0){
            success=true;
        }
        response.sendRedirect("bookMarkGroup.jsp?updatesuccess="+success);

    }

}
