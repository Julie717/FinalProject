package com.buyalskaya.fitclub.controller.command.impl.navigation;

import com.buyalskaya.fitclub.controller.AttributeName;
import com.buyalskaya.fitclub.controller.Router;
import com.buyalskaya.fitclub.controller.command.Command;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.Workout;
import com.buyalskaya.fitclub.model.service.ServiceFactory;
import com.buyalskaya.fitclub.model.service.WorkoutService;
import com.buyalskaya.fitclub.util.ConfigurationManager;
import com.buyalskaya.fitclub.util.PageConfigName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The type High workout page command.
 * This command allows to go to the page with workouts of high level
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class HighWorkoutPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        try {
            WorkoutService workoutService = ServiceFactory.getInstance().getWorkoutService();
            List<Workout> workouts = workoutService.findByLevel(Workout.Level.HIGH);
            request.setAttribute(AttributeName.WORKOUTS_HIGH, workouts);
            page = ConfigurationManager.getProperty(PageConfigName.HIGH_WORKOUT_PAGE);
        } catch (ServiceException ex) {
            logger.log(Level.ERROR, ex);
            page = ConfigurationManager.getProperty(PageConfigName.ERROR_500);
        }
        return new Router(page);
    }
}