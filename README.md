# How EAN-13 Barcodes work
As the name already tells us, the EAN-13 barcode contains 13 digits in total. 12 of these digits contain information about 
<ul>
 <li> a country prefix </li>
 <li> the number of the manufacterer who produced the product </li>
 <li> the product number itself </li>
</ul>

## The check digit and how it is calculated
The 13th and last digit is a check number to check if the scanner has read the barcode correctly. This check number is calculated as follows:
if you number the digits of the EAN-13 code with the numbers 1-12, every number that is numbered with an odd number is added together, 
every number that is numbered with an even number is also added together, but the sum of these odd numbered numbers is multiplied by 3 after that.
Next, you add the two sums together and calculate the difference to the next tens. This difference is your check number. Let me show you this with an example:

EAN-13 number: 0701197205291 (only consists of 12 digits because the check digit will be appended later)

lets number the digits with a range of 1-12:

Number|`1`|`2`|`3`|`4`|`5`|`6`|`7`|`8`|`9`|`10`|`11`|`12`|
------|-|-|-|-|-|-|-|-|-|-|-|-|
EAN-13 digit|0|7|0|1|1|9|7|2|0|5|2|9|

-> 0+0+1+7+0+2 = 10 (odd numbered numbers were added together)

-> (7+1+9+2+5+9) * 3 = 99 (even numbered numbers were added together, but were also multiplied by 3 at the end)

--> 10 + 99 = 109 (the two sums were added together)

--> 110 (next tens) - 109 = <b>1</b>

and there we have it, the check number is 1. That wasn´t that hard, right ;-)? If you want to see how to implement that in Java, feel free to look it up in the source code above before we actually start to understand what these black lines in the barcode mean.

If the sum of the two sums would already be a tens, the check digit would be 0 (for example the check digit of 644606181130 is 0)

After you have calculated the check digit, it is appended to the others: <br>
for example if the EAN-13 number with currently only 12 digits is 764838694596 and the check digit we calculated is 1, we just append it to the other digits and we get the full EAN-13 number which then also has 13 digits (7648386945961)

## Examples
![9582904492392](https://user-images.githubusercontent.com/94389494/172921188-97db97f6-8c97-4ca6-8cf1-629228d39841.png)
![1198038002826](https://user-images.githubusercontent.com/94389494/172921231-7362f6f9-094d-44eb-bb3d-3e5e682259fb.png)
![4853539853632](https://user-images.githubusercontent.com/94389494/172921289-a599ee17-13e5-4ec9-806a-befe685cfc50.png)
![6826673591515](https://user-images.githubusercontent.com/94389494/172921319-6f2e8818-1b82-489e-872c-9a815c7b9887.png)

