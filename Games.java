//Fernando Tortosa López - Development Enviroments
//Exercise for Units 1 to 4

import java.util.Scanner;

public class Games
{
    public static void main(String[] args)
    {
        //If there's only 1 argument we assume user wants to play lottery
        if (args.length == 1)
        {
            if (args[0].equals("lottery"))
            {
                playLottery();
            }

            else
            {
                System.out.println("Wrong input");
            }
        }

        //If there are 2 arguments we assume user wants to play Nim
        else if (args.length == 2)
        {
            if (args[0].equals("nim"))
            {
                int chips = 0;
                try
                {
                    chips = Integer.parseInt(args[1]);                    
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Not a valid number");
                }
                
                if(chips > 0 && chips < 31)
                {
                    playNim(chips);                    
                }
                else
                {
                    System.out.println("Only numbers between 1 and 30");
                }
            }

            else
            {
                System.out.println("Wrong input");
            }
        }
        
        else 
        {
            System.out.println("Wrong input");
        }
    }

    /*This function generates a random number and takes the minimun and maximun
    values for the range as arguments*/
    public static int generateNumber(int min, int max)
    {
        int result = min + (int)(Math.random() * (max - min + 1));
        return result;
    }

    //This function generates an array of 6 unique numbers and sorts them
    public static int[] generateLottery()
    {
        int[] lottery = new int[6];

        //Fill array with random unique numbers
        for (int i = 0; i < lottery.length;)
        {
            boolean unique = true;
            int aux;
            aux = generateNumber(1, 49);

            for (int j = 0; j < lottery.length; j++) //Check if number already exists in lottery array
            {
                if (aux == lottery[j])
                {
                    unique = false;
                    break;
                }
            }

            if (unique)
            {
                lottery[i] = aux;
                i++; //The outermost for loop only increases if number is unique
            }
            
        }

        //Sort by Bubble sort method
        for (int i = 0; i < lottery.length - 1; i++)
        {
            for (int j = i + 1; j < lottery.length; j++)
            {
                if (lottery[i] > lottery[j])
                {
                    int aux = lottery[i];
                    lottery[i] = lottery[j];
                    lottery[j] = aux;
                }
            }
        }

        //Returns sorted array
        return lottery;
    }

    //This function checks how many numbers coincide between both user and winner arrays
    public static int checkLottery(int[] user, int[] winner)
    {
        //This variable keeps track of the amount of numbers that coincide
        int amount = 0;

        //Check every number in user[] to see if they appear once on winner[]
        for (int i = 0; i < user.length; i++)
        {
            for (int j = 0; j < winner.length; j++)
            {
                if (user[i] == winner[j])
                {
                    amount ++;
                    break;
                }
            }
        }

        //Returns amount of numbers that appear in both arrays
        return amount;
    }

    //This function uses all other lottery functions to create the game
    public static void playLottery()
    {
        //Ask user for 6 numbers and keep them in user array
        System.out.println("Enter your 6 number combination:");
        int[] user = new int[6];

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 6; i++)
        {
            user[i] = sc.nextInt();
        }
        
        //Generate and show winner combination
        System.out.println("This is the winner combination:");
        int[] lottery = generateLottery();
        for (int num : lottery)
        {
            System.out.print(num + " ");
        }
        
        //Line break and show number of hits
        System.out.println();
        System.out.println("You have " + checkLottery(user, lottery) + " hits.");
    }

    //This function plays the game of Nim against the computer
    public static void playNim(int chips)
    {
        Scanner sc = new Scanner(System.in);
        int user, computer; //To store number of chips substracted each turn

        while(chips > 0)
        {
            System.out.println("There are " + chips + " chips left");

            //User turn
            System.out.println("How many chips do you take?");
            user = sc.nextInt();
            
            //Check user only takes up to 3 chips
            while(user > 3)
            {
                System.out.println("You can only take between 1 or 3 chips. How many do you take?");
                user = sc.nextInt();
            }

            //If there are less than 3 chips that becomes the new max number of chips user can take
            if (chips <= 3)
            {
                while(user > chips)
                {
                    System.out.println("There are only " + chips + " left. Pick a lower number");
                    user = sc.nextInt();
                }
            }

            //After pertinent checks, substract the no. of chips user chose
            chips -= user;

            //Check if user won after their turn
            if (chips == 0)
            {
                System.out.println("You win!");
                break;
            }

            //Show how many chips are left before computer takes its turn
            System.out.println("There are " + chips + " chips left");

            //Check that computer cant take more than 3 or more than the no. of chips left if chips <= 3
            if (chips <= 3)
            {
                computer = generateNumber(1, chips);
            }
            else
            {
                computer = generateNumber(1, 3);    
            }

            //Show how many chips the computer took and substract them from total
            System.out.println("The computer takes " + computer + " chips");
            chips -= computer;

            //Check if computer won
            if (chips == 0)
            {
                System.out.println("You lose!");
                break;
            }
        }
    }
}
