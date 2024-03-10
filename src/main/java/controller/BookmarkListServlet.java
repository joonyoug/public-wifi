package controller;

import db.BookMarkListService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*

    북마크 리스트 추가 삭제

 */


@WebServlet("/BookmarkListServlet")
public class BookmarkListServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String action=request.getParameter("action");
        if(action.equals("delete")){
            deleteBookMarkList(request,response);
        }
        else if(action.equals("add")){
            addBookMarkList(request,response);
        }

    }
    private void addBookMarkList(HttpServletRequest request,HttpServletResponse response)
            throws IOException {

        String bookmarkId=request.getParameter("bookmark_id");
        String X_SWIFI_MGR_NO=request.getParameter("X_SWIFI_MGR_NO");
        String distance=request.getParameter("distance");
        boolean success=false;
        int affected;

        BookMarkListService bookMarkListService =new BookMarkListService();
        affected=bookMarkListService.addBookMarkList(bookmarkId,X_SWIFI_MGR_NO);

        if(affected>0) {
           success=true;
        }
        response.sendRedirect("bookMarkList.jsp?addsuccess="+success

        );
    }
    private void deleteBookMarkList(HttpServletRequest request,HttpServletResponse response) throws IOException {

        String id=request.getParameter("id");
        boolean success=false;

        BookMarkListService bookMarkListService=new BookMarkListService();
        int affected=bookMarkListService.deleteBookmarkList(id);

        if(affected>0){
            success=true;
        }
        response.sendRedirect("bookMarkList.jsp?success="+success);

    }


}
