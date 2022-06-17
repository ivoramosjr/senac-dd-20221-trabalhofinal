package petshop.model.services;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import petshop.model.business.ServicoBusiness;
import petshop.model.controllers.PetController;
import petshop.model.dtos.response.PetResponseRelatorioDTO;
import petshop.model.dtos.response.RelatorioAtendimentoDTO;
import petshop.model.dtos.response.ServicoResponseRelatorioDTO;

public class PdfService {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Gerar PDF Pets cadastrado
        public void gerarRelatorioPets() {
            PetController petController = new PetController();

            //RelatorioPetDTO relatorioPet = petController.gerarRelatorio();

            List<PetResponseRelatorioDTO> petsDTO = petController.listAllRelatorio();
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
                    Paragraph titlePet = new Paragraph(new Phrase(14F , "Pet - "+pet.getIdPet(), FontPetTitle));
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

        ArrayList<ServicoResponseRelatorioDTO> servicoDTOS = (ArrayList<ServicoResponseRelatorioDTO>) servicoBusiness.getServices();

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

                    String status = servico.getStatus();
                    identation = new Paragraph(("Status: "+status));
                    identation.setIndentationLeft(20);
                    document.add(identation);

                    identation = new Paragraph(("Valor: "+servico.getValor()));
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

    public void gerarRelatorioAtendimento(String path, RelatorioAtendimentoDTO relatorio) {
        Document document = new Document();
        try{
            //AQUI EU PASSO O PATH E COM O NOME DO RELATÓRIO
            PdfWriter.getInstance(document, new FileOutputStream(path+"//Relatório atendimentos - "+dtf.format(LocalDateTime.now())+".pdf", false));
            document.open();
            Font fontTitle = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
            Paragraph title = new Paragraph(new Phrase(14F , "Relatorio Atendimentos", fontTitle));
            title.setAlignment(Element.ALIGN_CENTER);

            document.add(title);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Número de atendimentos: "+(relatorio.getNumeroAtendimentos())));
            document.add(new Paragraph("Atendimentos agendados: "+(relatorio.getAtendimentosAgendados())));
            document.add(new Paragraph("Atendimentos realizados: "+(relatorio.getAtendimentosRealizados())));
            document.add(new Paragraph("Atendimentos cancelados: "+(relatorio.getAtendimentosCancelados())));
            document.add(new Paragraph("Data do último atendimento: "+(relatorio.getUltimoAtendimento())));
            document.add(new Paragraph("Tipo do pet mais atendido: "+(relatorio.getTipoPetMaisAtendido())));
            document.add(new Paragraph("Valor total dos atendimentos (Realizados): "+(relatorio.getValorTotalAtendimentosRealizados())));

            if(!relatorio.getListaAtendimentos().isEmpty()){
                document.add(new Paragraph(" "));
                Font fontTable = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
                Paragraph tableTitle = new Paragraph(new Phrase(14F , "Lista de atendimentos", fontTable));
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(tableTitle);
                document.add(new Paragraph(" "));
                AtomicInteger count = new AtomicInteger(1);
                PdfPTable tabelaAtendimentos = new PdfPTable(7);
                tabelaAtendimentos.setTotalWidth(new float[]{
                        20, 80, 80, 100, 100, 80, 80
                });
                tabelaAtendimentos.setLockedWidth(true);

                PdfPCell header1 = new PdfPCell(new Phrase("#"));
                header1.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaAtendimentos.addCell(header1);

                PdfPCell header2 = new PdfPCell(new Phrase("Pet"));
                header1.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaAtendimentos.addCell(header2);

                PdfPCell header3 = new PdfPCell(new Phrase("Tipo do pet"));
                header1.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaAtendimentos.addCell(header3);

                PdfPCell header4 = new PdfPCell(new Phrase("Nome do serviço"));
                header1.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaAtendimentos.addCell(header4);

                PdfPCell header5 = new PdfPCell(new Phrase("Valor serviço"));
                header1.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaAtendimentos.addCell(header5);

                PdfPCell header6 = new PdfPCell(new Phrase("Data do atendimento"));
                header1.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaAtendimentos.addCell(header6);

                PdfPCell header7 = new PdfPCell(new Phrase("Status atendimento"));
                header1.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaAtendimentos.addCell(header7);

                relatorio.getListaAtendimentos().forEach(atendimento ->{
                    tabelaAtendimentos.addCell(count.toString());
                    tabelaAtendimentos.addCell(atendimento.getPetNome());
                    tabelaAtendimentos.addCell(atendimento.getPetTipoAnimal());
                    tabelaAtendimentos.addCell(atendimento.getServicoNome());
                    tabelaAtendimentos.addCell(atendimento.getServicoValor());
                    tabelaAtendimentos.addCell(atendimento.getDataAtendimento());
                    tabelaAtendimentos.addCell(atendimento.getStatusAtendimento());
                    count.getAndIncrement();
                });
                document.add(tabelaAtendimentos);
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
