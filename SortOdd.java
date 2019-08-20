import java.util.*;
public class Kata {
  public static int[] sortArray(int[] array)
{
    //temp variable for holding larger value for switching
    int temp = 0;

    for (int i = 0; i < array.length; i++)
    {
        //If the value is 'even' continue with outer loop
        if(array[i] % 2 == 0)
           continue;

        //Inner loop to compare array values
        for(int j = (i + 1); j < array.length; j++)
        {
            //If this value is not even do comparison
            if(array[j] % 2 != 0)
            {
                //If the left value is greater than the right value
                //swap them
                if(array[i] > array[j])
                {
                   temp = array[i];
                   array[i] = array[j];
                   array[j] = temp;
                }
            }
        }
    }

    return array;
}
}
