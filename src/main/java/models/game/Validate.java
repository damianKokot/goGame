package models.game;

public class Validate {
      /*

   public void execute(String command) throws CommandNotFoundException, PushException, UserExistsException, ForbiddenMoveException {
      String[] args = splitCommand(command);
      history.add(command);

      switch (args[0]) {
         case "push":
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            if(!panel.isValid(x, y, turn == idPlayer1? 1 : 2))
               throw new ForbiddenMoveException("You can't leave that here");
            panel.push(x, y, turn == idPlayer1? 1 : 2);
            break;
         case "add_user":
            if(idPlayer2 == -1) {
               throw new UserExistsException("You have just chosen an user");
            } else if (idPlayer2 == idPlayer1) {
               throw new UserExistsException("You can't play with yourself");
            } else {
               idPlayer2 = Integer.parseInt(args[1]);
            }
            break;
         case "pas":
         if(history.size() > 0 && history.get(history.size() - 1).equals("pas")) {
            finish();
         }
         break;
         case "end":
            finish();
            break;
      }
   }

   public String getMessage() {
      return message;
   }


   private void finish() {
      message = "Player " + ((turn == idPlayer1)? idPlayer2 : idPlayer1) + "won";
   }
   private void sumPoints() {

   }


   private String[] splitCommand(String command) throws CommandNotFoundException {
      String[] args = command.toLowerCase().split(" ");

      try {
         Integer.parseInt(args[0]);
      } catch (NumberFormatException e) {
         throw new CommandNotFoundException("Wrong user format");
      }

      switch (args[1]) {
         case "push":
            try {
               Integer.parseInt(args[2]);
               Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
               throw new CommandNotFoundException("Wrong arguments!");
            }
            break;
         case "add_user":
            try {
               Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
               throw new CommandNotFoundException("Wrong arguments!");
            }
            break;
         case "pas":
         case "end":
            break;
         default:
            throw new CommandNotFoundException("Command not found");
      }

      return args;
   }

    */

}
