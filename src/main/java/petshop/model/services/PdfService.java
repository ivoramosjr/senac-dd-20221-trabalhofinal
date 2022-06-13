package petshop.model.services;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import petshop.model.business.AtendimentoBusiness;
import petshop.model.business.PetBusiness;
import petshop.model.business.ServicoBusiness;
import petshop.model.dtos.AtendimentoDTO;
import petshop.model.dtos.PetDTO;
import petshop.model.dtos.ServicoDTO;
import petshop.model.enums.StatusAtendimentoEnum;

public class PdfService {

        // Gerar PDF Pets cadastrado
        public void gerarRelatorioPets() {
            PetBusiness petBusiness = new PetBusiness();

            ArrayList<PetDTO> petsDTO = (ArrayList<PetDTO>) petBusiness.listAll();
            System.out.println(petsDTO);

            Document document = new Document();

            try {
                PdfWriter.getInstance(document, new FileOutputStream("documento.pdf"));
                document.open();
                Font fontTitle = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
                Paragraph title = new Paragraph(new Phrase(14F , "Relatorio pets cadastrados", fontTitle));
                title.setAlignment(Element.ALIGN_CENTER);

                document.add(title);
                document.add(new Paragraph(" "));
                document.add(new Paragraph(("Total pets cadastrados: "+petsDTO.size())));

                Font FontPetTitle = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
                petsDTO.forEach((pet)->{
                    Paragraph titlePet = new Paragraph(new Phrase(14F , "Pet - "+pet.getIdPet().toString(), FontPetTitle));
                    try {
                        document.add(new Paragraph(" "));
                        document.add(titlePet);

                        Paragraph identation = new Paragraph(("Nome: "+pet.getNome()));
                        identation.setIndentationLeft(20);
                        document.add(identation);

                        identation = new Paragraph(("Nome do dono: "+pet.getNomeDono()));
                        identation.setIndentationLeft(20);
                        document.add(identation);

                        identation = new Paragraph(("Raça: "+pet.getRaca()));
                        identation.setIndentationLeft(20);
                        document.add(identation);

                        identation = new Paragraph(("Pontos de fidelidade: "+pet.getPontosFidelidade()));
                        identation.setIndentationLeft(20);
                        document.add(identation);

                        identation = new Paragraph(("Tipo do animal: "+pet.getTipoAnimal()));
                        identation.setIndentationLeft(20);
                        document.add(identation);

                        identation = new Paragraph(("Data de nascimento: "+pet.getDataNascimento()));
                        identation.setIndentationLeft(20);
                        document.add(identation);

                    } catch (DocumentException e) {
                        throw new RuntimeException(e);
                    }

                });

            } catch (FileNotFoundException e) {

                e.printStackTrace();
            } catch (DocumentException e) {

                e.printStackTrace();
            } finally {
                document.close();
            }

            try {
                Desktop.getDesktop().open(new File("documento.pdf"));
            } catch (IOException e) {

                e.printStackTrace();
            }

        }


    // Gerar PDF Pets cadastrado
    public void gerarRelatorioServicos() {
        ServicoBusiness servicoBusiness = new ServicoBusiness();

        ArrayList<ServicoDTO> servicoDTOS = (ArrayList<ServicoDTO>) servicoBusiness.getServices();

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("documento.pdf"));
            document.open();
            Font fontTitle = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
            Paragraph title = new Paragraph(new Phrase(14F , "Relatorio serviços cadastrados", fontTitle));
            title.setAlignment(Element.ALIGN_CENTER);

            document.add(title);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(("Total serviços cadastrados: "+servicoDTOS.size())));

            Font FontPetTitle = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
            servicoDTOS.forEach((servico)->{
                Paragraph titlePet = new Paragraph(new Phrase(14F , "Serviço - "+servico.getIdServico().toString(), FontPetTitle));
                try {
                    document.add(new Paragraph(" "));
                    document.add(titlePet);

                    Paragraph identation = new Paragraph(("Nome: "+servico.getNome()));
                    identation.setIndentationLeft(20);
                    document.add(identation);

                    identation = new Paragraph(("Descrição: "+servico.getDescricao()));
                    identation.setIndentationLeft(20);
                    document.add(identation);

                    String status = (servico.getStatus())?"Ativo":"Inativo";
                    identation = new Paragraph(("Status: "+status));
                    identation.setIndentationLeft(20);
                    document.add(identation);

                    identation = new Paragraph(("Valor: "+formatarValor(servico.getValor())));
                    identation.setIndentationLeft(20);
                    document.add(identation);

                    identation = new Paragraph(("Quantidade de atendimentos: "+servico.getQuantidadeAtendimentos()));
                    identation.setIndentationLeft(20);
                    document.add(identation);


                } catch (DocumentException e) {
                    throw new RuntimeException(e);
                }

            });

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (DocumentException e) {

            e.printStackTrace();
        } finally {
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File("documento.pdf"));
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

        //Formatar valor
        private String formatarValor(Double valor) {
            DecimalFormat decimalFormat = new DecimalFormat("R$ 0.00");
            return decimalFormat.format(valor);

        }
}
