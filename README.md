# Treasure-Hunt
A console application to find the path to the treasure on a map of tunnels stored in a simple text file.

## Description
The map is used by the application to determine if the explorer can move in a certain direction.

**Input Map:**

<img src="https://github.com/DhwaniSondhi/Treasure-Hunt/blob/master/input1.png" alt="drawing" width="300"/> 

- "–" character indicates that you are free to move in this direction. 
- "#" character indicates that you cannot move any further in this direction and you should go somewhere else. 
- "@" character indicates the location of the treasure. 
- The search will start at the top.

**Output Map:**

<img src="https://github.com/DhwaniSondhi/Treasure-Hunt/blob/master/output1.PNG" alt="drawing" width="300"/> 

- Indicates success or failure if the treasure was found or not.
- The map will been updated to indicate how the walk was done. 
- "+" characters indicate the path that led to the treasure. 
- "!" characters indicate the tunnels that were tried but did not to lead to a viable path.


***Another input and output combination:***


**Input Map:**            |  **Output Map:**
:-------------------------:|:-------------------------:
![](https://github.com/DhwaniSondhi/Treasure-Hunt/blob/master/input2.png)  |  ![](https://github.com/DhwaniSondhi/Treasure-Hunt/blob/master/output2.PNG)
