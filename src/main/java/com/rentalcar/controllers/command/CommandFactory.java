package com.rentalcar.controllers.command;

import com.rentalcar.controllers.command.account.Login;
import com.rentalcar.controllers.command.account.Logout;
import com.rentalcar.controllers.command.account.Signup;
import com.rentalcar.controllers.command.admin.AddCar;
import com.rentalcar.controllers.command.admin.ViewOrders;
import com.rentalcar.controllers.command.base.Error;
import com.rentalcar.controllers.command.base.Index;
import com.rentalcar.controllers.command.base.Language;
import com.rentalcar.controllers.command.rental.NewOrder;
import com.rentalcar.controllers.command.rental.Search;

import static com.rentalcar.constants.CommandConstants.Names.*;

import java.util.HashMap;

/**
 *  Singleton command factory, which return command according to http servlet request
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

    /**
     * @param name command name
     * @return  site command which corresponds to input parameter
     * or error command in case of invalid name
     */
    public Command getCommand(String name){
        Command command = commands.get(name.substring(1));
        return (command == null) ? commands.get(ERROR): command;
    }

    private void initCommandPool(){

        /* base site commands */
        commands.put(BASE, new Index());
        commands.put(ERROR, new Error());
        commands.put(LANG, new Language());

        /* account */
        commands.put(LOGIN, new Login());
        commands.put(LOGOUT, new Logout());
        commands.put(SIGN_UP, new Signup());

        /* user rental */
        commands.put(FIND, new Search());
        commands.put(RENT_CAR, new NewOrder());

        /* admin panel */
        commands.put(ADD_CAR, new AddCar());
        commands.put(SHOW_ORDERS, new ViewOrders());

    }
}
