package tutorial;


import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zul.*;
import org.zkoss.zul.ext.Selectable;

public class SearchController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Textbox keywordBox;
	@Wire
	private Button deleteButton;
	@Wire
	private Listbox carListbox;
	@Wire
	private Label modelLabel;
	@Wire
	private Label makeLabel;
	@Wire
	private Label priceLabel;
	@Wire
	private Label descriptionLabel;
	@Wire
	private Layout descriptionPane;
	@Wire
	private Image previewImage;
	
	
	private CarService carService = new CarServiceImpl();

//	On creation of the listbox object use the findAll method from carService to display the data
	@Listen("onCreate = #carListbox")
	public void getData(){
		List<Car> result = carService.findAll();
		carListbox.setModel(new ListModelList<Car>(result));
	}
	
	@Listen("onClick = #deleteButton")
	public void delete(){
		Set<Car> selection = ((Selectable<Car>)carListbox.getModel()).getSelection();
		if (selection!=null && !selection.isEmpty()){
			Car selected = selection.iterator().next();	
	        carService.remove(selected);
			getData();
			deleteButton.setDisabled(true);	
			descriptionPane.setVisible(false);
		}
	} 
	
	@Listen("onClick = #searchButton")
	public void search(){
		String keyword = keywordBox.getValue();
		List<Car> result = carService.search(keyword);
		carListbox.setModel(new ListModelList<Car>(result));
	}
	
	@Listen("onSelect = #carListbox")
	public void showDetail(){
		deleteButton.setDisabled(false);
		descriptionPane.setVisible(true);
		Set<Car> selection = ((Selectable<Car>)carListbox.getModel()).getSelection();
		if (selection!=null && !selection.isEmpty()){
			Car selected = selection.iterator().next();
			previewImage.setSrc(selected.getPreview());
			modelLabel.setValue(selected.getModel());
			makeLabel.setValue(selected.getMake());
			priceLabel.setValue(selected.getPrice().toString());
			descriptionLabel.setValue(selected.getDescription());
		}
	}
}
