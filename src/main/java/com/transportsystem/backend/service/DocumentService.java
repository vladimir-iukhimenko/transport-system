package com.transportsystem.backend.service;

import com.transportsystem.backend.model.DocumentEmployee;
import com.transportsystem.backend.model.DocumentTransport;
import com.transportsystem.backend.repository.DocumentEmployeeRepository;
import com.transportsystem.backend.repository.DocumentTransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transportsystem.backend.repository.DocumentRepository;
import com.transportsystem.backend.model.Document;

import java.util.List;

/**
 * Created by DZCKJS0 on 18.11.2019.
 */
@Service
public class DocumentService {
    private DocumentRepository documentRepository;
    private DocumentTransportRepository documentTransportRepository;
    private DocumentEmployeeRepository documentEmployeeRepository;

    @Autowired
    public void setDocumentDAO(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Transactional
    public List<DocumentTransport> getAllTransportDocuments()
    {
        return documentTransportRepository.findAll();
    }

    @Transactional
    public List<DocumentEmployee> getAllEmployeeDocuments() {return documentEmployeeRepository.findAll();}

    @Transactional
    public void add(Document document)
    {
        documentRepository.save(document);
    }

    @Transactional
    public void delete(Document document)
    {
        documentRepository.delete(document);
    }

    @Transactional
    public void edit(Document document)
    {
        documentRepository.save(document);
    }

    @Transactional
    public Document getDocumentTransportById(Integer id)
    {
        return documentTransportRepository.findById(id).orElse(null);
    }

    @Transactional
    public Document getDocumentEmployeeById(Integer id) {
        return documentEmployeeRepository.findById(id).orElse(null);
    }
}
