package core.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

// if~else문이 많아지면 추상화를 통하여
// 각 분기를 클래스로 나눈다.
public class JsonView implements View{

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(mapper.writeValueAsString(createModel(request)));
    }

    private Map<String, Object> createModel(HttpServletRequest request){
        Enumeration<String> names = request.getAttributeNames();
        Map<String, Object> model = new HashMap<>();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            model.put(name, request.getAttribute(name));
        }
        return model;
    }
}
