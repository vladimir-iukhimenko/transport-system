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

    public ModelAndView listDocuments(List<Document> documents,String type)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("documents");
        modelAndView.addObject("documents", documents);
        modelAndView.addObject("type",type);
        return modelAndView;
    }

    @RequestMapping(value = "/transportdocs", method = RequestMethod.GET)
    public ModelAndView listDocumentTransports() {
        List<Document> transportdocuments = documentService.getAllTransportDocuments();
        return listDocuments(transportdocuments,"DocumentTransport");
    }

    @RequestMapping(value = "/employeedocs", method = RequestMethod.GET)
    public ModelAndView listDocumentEmployees() {
        List<Document> employeedocuments = documentService.getAllEmployeeDocuments();
        return listDocuments(employeedocuments, "DocumentEmployee");
    }

    @RequestMapping(value = "/addtransportdoc", method = RequestMethod.GET)
    public ModelAndView addDocumentTransport()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editordocuments");
        modelAndView.addObject("type","DocumentTransport");
        return modelAndView;
    }

    @RequestMapping(value = "/addtransportdoc", method = RequestMethod.POST)
    public ModelAndView addDocumentTransport(@ModelAttribute("document") DocumentTransport document)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transportdocs");
        documentService.add(document);
        return modelAndView;
    }

    @RequestMapping(value = "/addemployeedoc", method = RequestMethod.GET)
    public ModelAndView addDocumentEmployee()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editordocuments");
        modelAndView.addObject("type","DocumentEmployee");
        return modelAndView;
    }

    @RequestMapping(value = "/addemployeedoc", method = RequestMethod.POST)
    public ModelAndView addDocumentEmployee(@ModelAttribute("document") DocumentEmployee document)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employeedocs");
        documentService.add(document);
        return modelAndView;
    }

    @RequestMapping(value = "/edittransportdoc/{id}", method = RequestMethod.GET)
    public ModelAndView editDocumentTransport(@PathVariable("id") Integer id)
    {
        Document document = documentService.getDocumentTransportById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editordocuments");
        modelAndView.addObject("document",document);
        modelAndView.addObject("type","DocumentTransport");
        return modelAndView;
    }

    @RequestMapping(value = "/edittransportdoc", method = RequestMethod.POST)
    public ModelAndView editDocumentTransport(@ModelAttribute("document") DocumentTransport document)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transportdocs");
        documentService.edit(document);
        return modelAndView;
    }

    @RequestMapping(value = "/editemployeedoc/{id}", method = RequestMethod.GET)
    public ModelAndView editDocumentEmployee(@PathVariable("id") Integer id)
    {
        Document document = documentService.getDocumentEmployeeById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editordocuments");
        modelAndView.addObject("document",document);
        modelAndView.addObject("type","DocumentEmployee");
        return modelAndView;
    }

    @RequestMapping(value = "/editemployeedoc", method = RequestMethod.POST)
    public ModelAndView editDocumentEmployee(@ModelAttribute("document") DocumentEmployee document)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employeedocs");
        documentService.edit(document);
        return modelAndView;
    }

    @RequestMapping(value = "/deletetransportdoc/{id}", method = RequestMethod.GET)
    public ModelAndView deleteDocumentTransport(@PathVariable("id") Integer id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/transportdocs");
        Document document = documentService.getDocumentTransportById(id);
        documentService.delete(document);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteemployeedoc/{id}", method = RequestMethod.GET)
    public ModelAndView deleteDocumentEmployee(@PathVariable("id") Integer id)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employeedocs");
        Document document = documentService.getDocumentEmployeeById(id);
        documentService.delete(document);
        return modelAndView;
    }
}
