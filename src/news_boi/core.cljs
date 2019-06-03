(ns news-boi.core
    (:require
      [reagent.core :as r]
      [news-boi.query :as q]
      [news-boi.render :as ui]
    )
)

;; -------------------------
;; Views

(defn home-page []
  [:div

    [:div
        [:h1 {:class "header"} "NEWS BOI"]
        [ui/search-bar]
    ]

    [ui/render-all-reddit @q/content]

  ]
)

;; -------------------------
;; Initialize app

(defn mount-root []
     (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
