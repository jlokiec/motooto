package pl.motooto.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.motooto.webapp.model.dto.SearchDto;
import pl.motooto.webapp.service.SearchResultService;
import pl.motooto.webapp.service.exception.SearchedFailedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SearchResultController {
    private SearchResultService searchResult;

    @Autowired
    SearchResultController(SearchResultService searchResult)
    {
        this.searchResult = searchResult;
    }

    @GetMapping("/search_result")
    public String initializeView(Model model, HttpServletRequest request) throws SearchedFailedException {
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null)
        {
            SearchDto dto = (SearchDto) httpSession.getAttribute("search_options");
            try {
                model.addAttribute("advertisements", searchResult.filter(dto));
            }
            catch(SearchedFailedException e)
            {
                throw new SearchedFailedException();
            }
        }
        return "search_result";
    }

}