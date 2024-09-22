class Solution:
    def calculate(self, s: str) -> int:
        N=len(s)
        prev=0
        i=0
        ans=0
        def getNumber(i):
            val=0
            sign=1
            while i<N and s[i]==' ':
                i+=1
            if i<N:
                if s[i]=='-':
                    sign=-1
                if not s[i].isdigit():
                    i+=1
            while i<N and s[i].isdigit():
                val=10*val+int(s[i])
                i+=1
            return (i,val*sign)
            
        while i<N:
            if s[i]==' ':
                i+=1
                continue
            if s[i]=='+' or s[i]=='-' or s[i].isdigit():
                i,val=getNumber(i)
                ans+=val
                prev=val
                continue

            t,val=getNumber(i+1)
            if s[i]=='*':
                ans=ans-prev+prev*val
                prev=prev*val
            elif s[i]=='/':
                plus=prev//val
                if prev<0:
                    plus=-(-prev//val)
                ans=ans-prev+plus
                prev=plus
            i=t

        return ans