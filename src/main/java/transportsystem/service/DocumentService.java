package transportsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transportsystem.dao.DocumentDAO;
import transportsystem.model.Document;

import java.util.List;

/**
 * Created by DZCKJS0 on 18.11.2019.
 */
@Service
public class DocumentService {
    private DocumentDAO documentDAO;

    @Autowired
    public void setDocumentDAO(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @Transactional
    public List<Document> getAllDocuments()
    {
        return documentDAO.getAllDocuments();
    }

    @Transactional
    public void add(Document document)
    {
        documentDAO.add(document);
    }

    @Transactional
    public void delete(Document document)
    {
        documentDAO.delete(document);
    }

    @Transactional
    public void edit(Document document)
    {
        documentDAO.edit(document);
    }

    @Transactional
    public Document getDocumentById(int id)
    {
        return documentDAO.getDocumentById(id);
    }

}
