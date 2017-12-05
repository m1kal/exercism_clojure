(ns run-length-encoding)

(defn encode-char [char occurences]
  (if (= 1 occurences)
    char
    (str occurences char)
  )
)

(defn next-char [string]
  (if (empty? string) ["" ""]
    (loop [char (first string) substring (rest string) occurences 1]
      (if
        (= char (first substring))
          (recur char (rest substring) (inc occurences))
          [(encode-char char occurences) substring]
      )
    )
  )
)

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [s]
  (loop [string s encoded ""]
    (if (empty? string)
      encoded
      (let [ret (next-char string)]
        (recur (last ret) (str encoded (first ret)))
      )
    )
  )
)

(defn parse [string]
  (let [match (re-find #"^(\d*)(.)(.*)" string)
        repetitions (nth match 1)
        occurences (if (empty? repetitions) 1 (read-string repetitions))
        char (nth match 2)
        remainder (nth match 3)
       ]
  [(clojure.string/join (repeat occurences char)) remainder]
  )
)

(defn run-length-decode
  "decodes a run-length-encoded string"
  [s]
  (loop [substring s encoded ""]
    (if (empty? substring)
      encoded
      (let [parsed (parse substring)]
        (recur (last parsed) (str encoded (first parsed)))
      )
    )
  )
)
