(ns news-boi.render
	(:require
		[clojure.pprint :as p]
		[news-boi.query :as q]
	)
)

(def typing false)

(defn search-bar []
	[:div
		[:input
			{	:class "search"
				:type "text"
				:autofocus "true"
				:placeholder "Type in your topic here."
				:value @q/search-word
				:on-change #(
					(reset! q/search-word (-> % .-target .-value))
				)
				:autocomplete "off"
				:autocorrect "off"
				:autocapitalize "off"
				:spellcheck "false"

				:on-key-press (fn [e]
						(if (= "Enter" (.-key e))
							(q/get-content @q/search-word)
						)
					)
		}]

	]
)

(defn get-timestamp [utc]
	(str "")
)

(defn render-reddit-post [{:keys [title permalink thumbnail url created_utc]}]
	[:div.col-sm {:class "article"}
		[:a {:href url :target "_blank"}
			[:h5 title]
			[:p (get-timestamp created_utc)]
		]
	]
)

(defn render-all-reddit [posts]
	[:div.container-fluid
		(for [posts-row (partition-all 3 posts)]
			^{:key posts-row}
			[:div.row
				(for [post posts-row]
					^{:key post}
					[render-reddit-post post]
				)
			]
		)
	]
)

(defn render-twitter-post [post])