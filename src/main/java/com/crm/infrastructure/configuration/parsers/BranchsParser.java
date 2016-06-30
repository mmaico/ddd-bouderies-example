package com.crm.infrastructure.configuration.parsers;

import com.crm.infrastructure.entity.Branch;
import com.crm.infrastructure.exceptions.InternalArchitectureException;
import com.google.common.collect.Lists;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class BranchsParser {

    private static final String ID = "id";
    private static final String NAME="name";
    private static final String FILE_NAME = "/configurations/user_branch.xml";


    public static List<Branch> getBranchs() {

        return loadBranchs();
    }


    @SuppressWarnings("unchecked")
    protected static List<Branch> loadBranchs() {
        SAXBuilder sab = new SAXBuilder();
        try {
            InputStream inputStream = findFile();
            Document doc = (Document) sab.build(inputStream);
            Element rootElement = (Element) doc.getRootElement();
            List<Branch> listBranchs = Lists.newArrayList();

            List<Element> childrenRoot = rootElement.getChildren();

            for (Element el : childrenRoot) {

                Branch branch = convertToBranch(el);
                listBranchs.add(branch);
            }

            return listBranchs;
        } catch (JDOMException e) {
            throw new InternalArchitectureException("Erro ao obter as configuracoes.", e);

        } catch (IOException e) {
            throw new InternalArchitectureException("Erro ao obter o arquivos de configuracoes.", e);
        }
    }

    @SuppressWarnings("unchecked")
    private static Branch convertToBranch(Element element) {
        String name = element.getAttributeValue(NAME);
        String id = element.getAttributeValue(ID);

        Branch branch = new Branch();
        branch.setId(new Long(id));
        branch.setName(name);

        return branch;

    }
    private static InputStream findFile() {

        try {
            return BranchsParser.class.getResourceAsStream(FILE_NAME);
        } catch (Exception e) {
            throw new IllegalArgumentException("Nao foi possivel encontrar o arquivo:" + FILE_NAME);
        }

    }

}
