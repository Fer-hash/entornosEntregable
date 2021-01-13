import java.util.Scanner;

public class Games
{
    public static int generateNumber(int min, int max)
    {
        /*This function generates a random number and takes the minimun and maximun
        values for the range as arguments*/
        int result = min + (int)(Math.random() * (max - min + 1));
        return result;
    }

    public static int[] generateLottery()
    {
        int[] lottery = new int[6];

        //Fill array with random numbers
        for (int i = 0; i < 6; i++)
        {
            lottery[i] = generateNumber(1, 49);
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

    public static void playLottery()
    {
        System.out.println("Enter your 6 number combination:");
        int[] user = new int[6];

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 6; i++)
        {
            user[i] = sc.nextInt();
        }
        
        System.out.println("This is the winner combination:");
        int[] lottery = generateLottery();
        for (int num : lottery)
        {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("You have " + checkLottery(user, lottery) + " hits.");
    }

    public static void playNim(int chips)
    {
        Scanner sc = new Scanner(System.in);
        int user, computer;
        while(chips > 0)
        {
            System.out.println("There are " + chips + " chips left");
            System.out.println("How many chips do you take?");
            user = sc.nextInt();
            while(user > 3)
            {
                System.out.println("You can only take between 1 or 3 chips. How many do you take?");
                user = sc.nextInt();
            }
            if (chips <= 3)
            {
                while(user > chips)
                {
                    System.out.println("There are only " + chips + " left. Pick a lower number");
                    user = sc.nextInt();
                }
            }
            chips -= user;
            if (chips == 0)
            {
                System.out.println("You win!");
                break;
            }
            System.out.println("There are " + chips + " chips left");
            if (chips <= 3)
            {
                computer = generateNumber(1, chips);
            }
            else
            {
                computer = generateNumber(1, 3);    
            }
            System.out.println("The computer takes " + computer + " chips");
            chips -= computer;
            if (chips == 0)
            {
                System.out.println("You lose!");
                break;
            }
        }
        
    }
    public static void main(String[] args)
    {
        playNim(20);
        //playLottery();
        /*int[] lottery = generateLottery();
        for (int num : lottery) 
        {
            System.out.print(num + " ");    
        }
        System.out.println();
        int[] user = new int[6];
        for(int i = 0; i < user.length; i++)
        {
            user[i] = generateNumber(1,49);   
        }
        for(int num : user)
        {
            System.out.print(num + " ");
        }
        System.out.println();
        int prueba = checkLottery(user, lottery);
        System.out.println(prueba); */
    }
}
