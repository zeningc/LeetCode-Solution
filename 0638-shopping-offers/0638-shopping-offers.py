class Solution:
    def shoppingOffers(self, price: List[int], special: List[List[int]], needs: List[int]) -> int:
        
    
        special.sort()
        self.curr = [0 for i in range(len(price)+1)]
        self.t = sum(needs)
        self.needs = needs
        self.res = float('inf')
        
        self.price = price
        self.util(special)
        
        return self.res

    def util(self, special):
    
        if self.curr[-1] > self.res:
            return

        
        
        

        for i in range(len(special)):
            flag = False
            if i!=0 and special[i][:len(special[i])-1] == special[i-1][:len(special[i])-1]:
                continue
            s = special[i]
            temp = 0
            
            for j in range(len(self.curr)):

                self.curr[j]+=s[j]
                if j!=len(self.curr)-1 and self.curr[j]>self.needs[j]:
                    flag = True
            
            if not flag:
                self.util(special)

            for j in range(len(self.curr)):
                self.curr[j]-=s[j]
                if j!=len(self.curr)-1:
                    temp+=(self.needs[j] - self.curr[j])*self.price[j]
            
            self.res = min(self.curr[-1] + temp, self.res)