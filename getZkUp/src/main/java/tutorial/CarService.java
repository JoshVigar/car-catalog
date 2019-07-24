package tutorial;

import java.util.List;

public interface CarService {
	
    /**
     * Store or modify a car in car store.
     */
    void store(String model, String make, String color, String description,  String preview, Integer price);
	
	/**
	 * Retrieve all cars in the catalog.
	 * @return all cars
	 */
	public List<Car> findAll();
	
	/**
	 * search cars according to keyword in model and make.
	 * @param keyword for search
	 * @return list of car that match the keyword
	 */
	public List<Car> search(String keyword);
}
