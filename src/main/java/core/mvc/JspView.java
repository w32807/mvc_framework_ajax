package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// if~else문이 많아지면 추상화를 통하여
// 각 분기를 클래스로 나눈다.
public class JspView implements View{

    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

    String viewName;

    public JspView(String viewName) {
        if(viewName == null){
            throw new NullPointerException("viewName is null. 이동할 url을 추가 해 주세요.");
        }
        this.viewName = viewName;
    }

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 결국 view로 보내는 역할은 HttpServletResponse의
        // SendRedirect 혹은 forward 메소드이다.
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            response.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
            return;
        }

        RequestDispatcher rd = request.getRequestDispatcher(viewName);
        rd.forward(request, response);
    }
}
