(ns hamming)

(defn distance [str1 str2]
  (if (= (count str1) (count str2))
      (->> (map = str1 str2) (remove identity) count)))

