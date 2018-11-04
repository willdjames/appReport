package br.appReport.main;

import static net.sf.jasperreports.engine.JRExporterParameter.JASPER_PRINT;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import br.appReport.conn.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class ReportMain {

	public static void main(String[] args) throws JRException, FileNotFoundException {
		
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.getConnection();
		
//		Compila para arquivo .jasper
		JasperCompileManager.compileReportToFile("Movimentacoes.jrxml");
		
//		Escreve ais informacoes no relatorio
		Map<String, Object> params = new HashMap<String, Object>();
		
//		Retorna O relatorio preenchido generico
		JasperPrint print = JasperFillManager.fillReport("Movimentacoes.jasper", params, connection);
	
//		Gera .pdf dee saida
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("Movimentacao.pdf"));
		
//		Gera o relatorio final
		exporter.exportReport();
		
		factory.close(connection);	

	}

}
