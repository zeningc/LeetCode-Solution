# 65. Valid Number
a valid number looks like this: [+/-]123[.456][[E/e][+/-]789]  
Obviously, a loop from left to right is needed, let's go throught the process step by step:  
> we let E/e as the divide of two number, the number preceeds E/e is almost the same as the one follows it, except that the latter one could not have "."  

let c be the current character  
### if c is sign(+/-)
end = false because a valid number cannot end with sign, e.g. 1+, 22-  
if (hasSign) we have met with sign, and there is no E/e between the two sign(otherwise hasSign will be set to false) -> false  
if (hasDigit) we have met with digit but there is no E/e betweem the digit and the sign, e.g. 1+, 2-
-> false  
hasSign = true  
### if c is digit
end = true because a valid number can end with digit, e.g. 123 123E123 12.2 .3  
hasDigit = true  
iterate through all the digits  
### if c is dot
if hasDigit end = true because a valid number can be ended with number followed by a dot, e.g. +12.    
else end = false  
there must be number on either side of the dot, otherwise return false  
if hasDot -> false there should be only one dot   
hasDot = true  
if hasE -> false dot cannot appear after E/e, e.g. 12E2.6  
### if c is E/e
end = false because a valid number cannot end with E/e, e.g. 123E  
if !hasDigit -> false because E/e must follow a decimal or integer  
if hasE -> false because E/e can appear only once  
hasDigit = false  
hasSign = false  
hasE = true  
### Other
return false



