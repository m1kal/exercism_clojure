(ns phone-number)

(defn strip [input]
  (clojure.string/replace input #"[^0-9]+" "")
)

(defn number [input]
  (cond  
    (re-matches #"\d{10}" (strip input)) (strip input)
    (re-matches #"1\d{10}" (strip input)) (subs (strip input) 1 11)
    :else "0000000000"
  )
)

(defn area-code [input]
 (subs (number input) 0 3)
)

(defn pretty-print [input]
 (str
   "(" (subs (number input) 0 3)
   ") " (subs (number input) 3 6)
   "-" (subs (number input) 6 10)
 )
)

