(ns allergies)

(def allergens {1 :eggs 2 :peanuts 4 :shellfish 8 :strawberries
                16 :tomatoes 32 :chocolate 64 :pollen 128 :cats})

(defn allergies [encoded]
  (map
    allergens
    (filter
      #(> (bit-and encoded %) 0)
      (map
        #(bit-shift-left 1 %)
        (range 8)))))

(defn allergic-to? [encoded candidate]
  (some #{candidate} (allergies encoded)))
