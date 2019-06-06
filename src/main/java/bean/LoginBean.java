package bean;

import entidade.Funcionalidade;
import entidade.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.UserDAO;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;

@ManagedBean
@RequestScoped
public class LoginBean {
    private String perfil = "";
    private Usuario usuario;
    private UserDAO userDAO;
    
    private DefaultMenuModel menuModel; 

    public DefaultMenuModel retornaMenu(Usuario usuarioTemp) {
        DefaultMenuModel menuTemp = new DefaultMenuModel();
        
        for(Funcionalidade funcionalidade:usuarioTemp.getPerfil().getFuncionalidades()){
            DefaultMenuItem item = new DefaultMenuItem();
            item.setUrl(funcionalidade.getUrl());
            item.setValue(funcionalidade.getRotulo());
            menuTemp.addElement(item);
        }
        
        return menuTemp;
    }
    
    public LoginBean() {
        this.userDAO = new UserDAO();
    }
    
    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public DefaultMenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(DefaultMenuModel menuModel) {
        this.menuModel = menuModel;
    }
    
    public String logar(){
        FacesContext context = FacesContext.getCurrentInstance();
        this.usuario = this.userDAO.retornarPorId(1L);
        context.getExternalContext().getSessionMap().put("perfil", this.usuario.getPerfil().getDescricao());
        this.menuModel = this.retornaMenu(this.usuario);

        //context.getExternalContext().getSessionMap().put("perfil", "qualquercoisa");       
        return "index";
    }
    
    public String deslogar(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }
}