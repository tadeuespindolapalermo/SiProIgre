package br.com.tadeudeveloper.siproigre.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

@Named("imagem")
@SessionScoped
public class ImagensBean extends AbstractBean {
	
	private static final long serialVersionUID = 1L;
	
	String path = "/home/tadeu/eclipse-workspace/java-ee/SiProIgre/WebContent/uploads";
	
	private List<String> imagens = new ArrayList<>();	
	
	public List<String> getImagens(){
		
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
        File pastaImagensTopo = new File(scontext.getRealPath("/uploads/"));
        if(!pastaImagensTopo.exists())pastaImagensTopo.mkdirs();
        File[] arquivos = pastaImagensTopo.listFiles();        
        for(File arquivo : arquivos){
            if(arquivo.isFile()){
                String ext = arquivo.getName().substring(arquivo.getName().lastIndexOf(".")).toLowerCase();
                if(ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".bmp") || ext.equals(".gif") || ext.equals(".png")) {
                    imagens.add("/uploads/" + arquivo.getName());
                }
            }
        }
        return imagens;
    }		
	
	public String refresh() {
		return "";
	}	

}
