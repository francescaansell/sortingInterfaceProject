_Sortinator 2000_

_Francesca Ansell_

_IST 311, Dan Welch_

####Performance characteristics of ListImpl implementation and comparison of implementations. 
For my list implementation I decided to sort inside of the `switchState()` as shown bellow. 
  ```java
   @Override
       public void switchState() {
           if (accepting) {
               bubbleSort();
           }
           this.accepting = !this.accepting;
       }
   ```
   
   I choose to do this to optimize runtime and memory. I originally wanted to implement merge sort but in the end decided to simply implement bubble sort due to time restraints on the project. The heapImpl is a more efficient implementation of the Sortinator Interface because it sorts as the nodes are added, making extraction much easier.  
  
  
####UML Diagram
![My UML Class Diagram](images/CaptureOfUML.png)
   
#### Hardest and Easiest Parts

The hardest part of this assignment was implementing the heapify method. I was not protecting the leaf nodes correctly, and I mistakenly begain using a while loop. After coming to office hours I was able to solve most of my problems in that I no longer had an infinite loop however, I was still having issues when the size of the array was 2. To combat that I added additional conditions to my if statement before comparing to short circut the statement. Then after my first two if statments I added another stating if the legth was two and the child was greater than the parent swap them. This is my resulting heapify function. 
```java
if (heapSize > 2 && left < heapSize && right < heapSize && order.compare(heap.get(left), heap.get(right)) < 0) {
            idxOfSmallest = left;
}
if (heapSize > 2 && right < heapSize && order.compare(heap.get(right), heap.get(idxOfSmallest)) < 0) {
    idxOfSmallest = right;
}
if(heapSize == 2 && order.compare(heap.get(0), heap.get(1)) > 0){
    swapElementsAt(0, 1);
}
if (idxOfSmallest != i) {
    swapElementsAt(i, idxOfSmallest);
    heapify(idxOfSmallest);
}

```
