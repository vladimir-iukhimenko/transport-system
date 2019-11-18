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

    @RequestMapping(value = "/transportdocs", method = RequestMethod.GET)
    public ModelAndView listtransportdocuments() {
        List<Document> transportdocuments = documentService.getAllTransportDocuments();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transportdocuments");
        modelAndView.addObject("transportdocuments",transportdocuments);
        return modelAndView;
    }

    @RequestMapping(value = "/addtransportdoc", method = RequestMethod.GET)
    public ModelAndView addtransportdocument()
    {
        List<Document> transportdocuments = documentService.getAllTransportDocuments();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editordocuments");
        modelAndView.addObject("transportdocuments", transportdocuments);
        return modelAndView;
    }

    @RequestMapping(value = "/addtransportdoc", method = RequestMethod.POST)
    public ModelAndView addTransportdocument(@ModelAttribute("document") DocumentTransport document)
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Document> transportmodels = documentService.getAllTransportDocuments();
        modelAndView.setViewName("redirect:/transportdocs");
        documentService.add(document);
        return modelAndView;
    }
}
