package com.rentalcar.controllers.command;

import com.rentalcar.controllers.command.account.Login;
import com.rentalcar.controllers.command.account.Logout;
import com.rentalcar.controllers.command.account.Signup;
import com.rentalcar.controllers.command.admin.Cars;
import com.rentalcar.controllers.command.admin.Orders;
import com.rentalcar.controllers.command.admin.UpdateOrder;
import com.rentalcar.controllers.command.rental.RentCar;
import com.rentalcar.controllers.command.rental.Search;
import com.rentalcar.controllers.services.AccountService;

import java.util.HashMap;

/**
 *  Form commands according to http servlet request uri
 */
public class CommandFactory {

    private HashMap<String, Command> commands;

    private static class SingletonHolder{
        private static CommandFactory instance = new CommandFactory();
    }

    private CommandFactory(){
        commands = new HashMap();
        initCommandPool();
    }

    public static CommandFactory getInstance() {
        return SingletonHolder.instance;
    }

    public Command getCommand(String commandName){
        Command command = commands.get(commandName);
        if (command == null)
            return commands.get("Error");
        return command;
    }

    private void initCommandPool(){
        commands.put("", new Index());
        commands.put("Error", new Error());

        commands.put("Login", new Login());
        commands.put("Logout", new Logout());
        commands.put("Signup", new Signup());

        commands.put("Search", new Search());
        commands.put("RentCar", new RentCar());

        commands.put("Orders", new Orders());
        commands.put("Cars", new Cars());
        commands.put("UpdateOrder", new UpdateOrder());

        commands.put("Language", new Language());

    }
}
