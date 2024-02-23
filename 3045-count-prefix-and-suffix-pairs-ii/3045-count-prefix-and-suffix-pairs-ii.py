class Solution:
    def countPrefixSuffixPairs(self, words: List[str]) -> int:

        def isPrefixAndSuffix (str1, str2):
            if str1 in wordLen:
                l = wordLen[str1]
            else:
                l = len(str1)
                wordLen[str1] = l
            if str2 in wordLen:
                l2 = wordLen[str2]
            else:
                l2 = len(str2)
                wordLen[str2] = l2
            if l > l2:
                return False
            for i in range(l):
                if not (str2[i] == str1[i] == str2[l2-l+i]):
                    return False
            return True

        wordLen = defaultdict(int)
        myDict = defaultdict(list)
        totalWordsLen = len(words)
        ans = 0
        for i in range(totalWordsLen):
            count = 0
            if words[i] in myDict:
                temp = 0
                for k in range(myDict[words[i]][0]+1, i):
                    if isPrefixAndSuffix(words[i], words[k]):
                        temp += 1
                count = max(myDict[words[i]][1] - temp - 1, 0)
                myDict[words[i]][0] = i
                myDict[words[i]][1] = count
            else:
                for j in range(i+1, totalWordsLen):
                    if isPrefixAndSuffix(words[i], words[j]):
                        count += 1
                myDict[words[i]] = [i, count]
            ans += count
        return ans
            