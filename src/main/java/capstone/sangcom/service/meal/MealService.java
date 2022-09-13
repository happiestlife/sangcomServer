package capstone.sangcom.service.meal;

import capstone.sangcom.entity.dto.mealSection.MealDTO;
import org.apache.tomcat.util.json.ParseException;

import java.util.List;

public interface MealService {
    public List<MealDTO> getMeals(String from, String to) throws ParseException, org.json.simple.parser.ParseException, java.text.ParseException;
}
