package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

@ManagedBean
@SessionScoped
public class menuBean {

    private DefaultMenuModel menuModel;

    /**
     * Creates a new instance of menuBean
     */
    public menuBean() {
        this.menuModel = this.retornaMenu();
    }

    public DefaultMenuModel retornaMenu() {
        DefaultMenuModel menuModel = new DefaultMenuModel();

        DefaultSubMenu subMenu = new DefaultSubMenu();
        subMenu.setLabel("SubMenu 1");
        DefaultMenuItem item = new DefaultMenuItem();
        item.setValue("Página 1");
        item.setUrl("pagina1.xhtml");
        subMenu.addElement(item);
    
        String perfil = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfil");
        if (perfil.equals("admin")) {
            item = new DefaultMenuItem();
            item.setValue("Pagina 2");
            item.setUrl("pagina2.xhtml");
            subMenu.addElement(item);
            
        }
        menuModel.addElement(subMenu);
        
        subMenu = new DefaultSubMenu();
        subMenu.setLabel("SubMenu 2");
        
        item = new DefaultMenuItem();
        item.setValue("Página 3");
        item.setUrl("pagina3.xhtml");
        
        subMenu.addElement(item);
        menuModel.addElement(subMenu);
        
        menuModel.addElement(item);

        menuModel.generateUniqueIds();

        return menuModel;
    }

    public DefaultMenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(DefaultMenuModel menuModel) {
        this.menuModel = menuModel;
    }
}
