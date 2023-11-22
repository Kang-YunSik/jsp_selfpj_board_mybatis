package Controller;

import DAO.MVCBoardDAO;
import VO.MVCBoardVO;
import utils.Encrypt;
import utils.JSFunction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Controller/pass.do")
public class PassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("mode", req.getParameter("mode"));
        req.getRequestDispatcher("/View/Pass.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 매개변수 저장
        String idx = req.getParameter("idx");
        String mode = req.getParameter("mode");

        // 암호화된 pass 읽기 ===========================
        String noEncPass = req.getParameter("pass");
        String encPass = Encrypt.getEncrypt(noEncPass);
        // 암호화된 pass 읽기 ===========================


        // String pass = req.getParameter("pass");

        // 비밀번호 확인
        MVCBoardDAO dao = new MVCBoardDAO();
        boolean confirmed = dao.confirmPassword(encPass, idx);

        if (confirmed) {  // 비밀번호 일치
            if (mode.equals("edit")) {  // 수정 모드
                HttpSession session = req.getSession();
                session.setAttribute("pass", encPass);
                resp.sendRedirect("../Controller/edit.do?idx=" + idx);
            }
            else if (mode.equals("delete")) {  // 삭제 모드
                dao = new MVCBoardDAO();
                MVCBoardVO vo = dao.selectView(idx);
                int result = dao.deletePost(idx);  // 게시물 삭제
                if (result == 1) {  // 게시물 삭제 성공 시 첨부파일도 삭제
                    String saveFileName = vo.getSfile();
                    utils.FileUtil.deleteFile(req, "/Uploads", saveFileName);
                }
                JSFunction.alertLocation(resp, "삭제되었습니다.", "../Controller/list.do");
            }
        }
        else {  // 비밀번호 불일치
            JSFunction.alertBack(resp, "비밀번호 검증에 실패했습니다.");
        }
    }
}
