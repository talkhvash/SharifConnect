package shared.request.myedu.professor;

import shared.request.FilterStudentForm;
import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.myedu.professor.RefreshProfessorSUListResponse;

public class RefreshProfessorSUListRequest extends Request {
    private final FilterStudentForm filter;

    public RefreshProfessorSUListRequest(FilterStudentForm filter) {
        this.filter = filter;
    }

    @Override
    public RefreshProfessorSUListResponse handle(RequestHandler handler) {
        return handler.refreshProfessorSUList(filter);
    }

    public FilterStudentForm getFilter() {
        return filter;
    }

}
