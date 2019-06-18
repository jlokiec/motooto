package pl.motooto.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import pl.motooto.webapp.model.dto.SearchDto;
import pl.motooto.webapp.service.SearchService;
import pl.motooto.webapp.service.exception.SearchedFailedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class SearchController {
    private static final String SEARCH_DTO = "searchDTO";

    private SearchService searchService;

    @Autowired
    SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String initializeView(Model model) throws SearchedFailedException {
        model.addAttribute(SEARCH_DTO, new SearchDto());
        try {
            model.addAttribute("advertisements", searchService.getAll());
        } catch (SearchedFailedException e) {
            throw new SearchedFailedException();
        }
        return "search";
    }

    @PostMapping("/search")
    public RedirectView searchAdvertisments(@ModelAttribute(SEARCH_DTO) SearchDto searchDto, BindingResult result, HttpServletRequest request, Model model) {
        RedirectView redirectView = new RedirectView();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("search_options", searchDto);
        redirectView.setUrl("/search_result");
        return redirectView;

    }
}
