(ns octal)


(defn to-decimal [number]
  (if (re-matches #".*[^0-7].*" number)
    0
    (reduce
      +
      (map-indexed
        #(*
          (bit-shift-left 1 (* 3 %1))
          (read-string (str %2)))
        (reverse number)))))

