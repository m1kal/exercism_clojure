(ns trinary)

(defn to-decimal [trinary]
  (int
    (apply +
      (map-indexed
        (fn [idx digit]
          (*
            (Math/pow 3 idx)
            (read-string (str digit))))
          (reverse
            (clojure.string/replace trinary #".*[^0-2].*" ""))))))
