package tutorial;


import java.util.LinkedList;
import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zul.*;

public class AddController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Textbox modelBox;
	@Wire
	private Textbox makeBox;
	@Wire
	private Textbox colorBox;
	@Wire
	private Textbox previewBox;
	@Wire
	private Textbox descriptionBox;
	@Wire
	private Intbox priceBox;
	
	private CarService carService = new CarServiceImpl();
	private List<Car> carList= new LinkedList<Car>();
	private Boolean validated = false;
	
    
	@Listen("onClick = #addButton")
	public void add(){
			carService.store(modelBox.getValue(),
							 makeBox.getValue(),
							 colorBox.getValue(),
							 descriptionBox.getValue(),
							 "/img/car1.png",
							 priceBox.getValue());
			alert("You have added a new vehicle!");
		}
}
