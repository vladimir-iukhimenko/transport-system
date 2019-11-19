package transportsystem.controller;

/**
 * Created by DZCKJS0 on 18.11.2019.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import transportsystem.model.Document;
import transportsystem.model.DocumentEmployee;
import transportsystem.model.DocumentTransport;
import transportsystem.service.DocumentService;
import java.util.*;
@Controller
public class DocumentController {
    private DocumentService documentService;

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public ModelAndView listdocuments(List<Document> documents)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("documents");
        modelAndView.addObject("documents", documents);
        return modelAndView;
    }

    @RequestMapping(value = "/transportdocs", method = RequestMethod.GET)
    public ModelAndView listtransportdocuments() {
        List<Document> transportdocuments = documentService.getAllTransportDocuments();
        return listdocuments(transportdocuments);
    }

    @RequestMapping(value = "/employeedocs", method = RequestMethod.GET)
    public ModelAndView listemployeedocuments() {
        List<Document> employeedocuments = documentService.getAllEmployeeDocuments();
        return listdocuments(employeedocuments);
    }

    @RequestMapping(value = "/addtransportdoc", method = RequestMethod.GET)
    public ModelAndView addtransportdocument()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editordocuments");
        return modelAndView;
    }

    @RequestMapping(value = "/addtransportdoc", method = RequestMethod.POST)
    public ModelAndView addTransportdocument(@ModelAttribute("document") DocumentTransport document)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transportdocs");
        documentService.add(document);
        return modelAndView;
    }

    @RequestMapping(value = "/addemployeedoc", method = RequestMethod.GET)
    public ModelAndView addemployeedocument()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editordocuments");
        return modelAndView;
    }

    @RequestMapping(value = "/addemployeedoc", method = RequestMethod.POST)
    public ModelAndView addEmployeedocument(@ModelAttribute("document") DocumentEmployee document)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employeedocs");
        documentService.add(document);
        return modelAndView;
    }
}
