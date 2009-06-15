package com.sixfootplus.blog

class BlogLinkController {

    def scaffold = true

    def list = {

        Map model = [:]

        model.links = BlogLink.findAll(
            [max: 10,
                offset: params.offset,
                sort: params.sort ?: 'id',
                order: params.order ?: 'desc'])
        model.linkCount = BlogLink.count()

        return model
    }

    def create = {

        def link = new BlogLink()
        link.properties = params

        return ['link':link, 'articles': BlogArticle.findAll(
                [sort: 'dateCreated',
                    order: 'desc'])]
    }

    def edit = {

        def link = BlogLink.get(params.id)
        def articles = BlogArticle.findAll(
            [sort: 'dateCreated',
                order: 'desc'])

        if (!link) {
            flash.message = "Link not found with id ${params.id}"
            redirect(action: list)
        } else {
            return [link: link, articles: articles]
        }
    }

    def show = {

        def link = BlogLink.get(params.id)

        if (!link) {
            flash.message = "Link not found with id ${params.id}"
            redirect(action: list)
        } else {
            return [link: link]
        }
    }

    def update = {

        def link = BlogLink.get(params.id)

        if (link) {
            link.properties = params
            if (link.save()) {
                flash.message = "Link ${params.id} updated!"
                redirect(action: show, id: link.id)
            } else {
                render(view: 'edit', model: [link: link])
            }
        } else {
            flash.message = "Link not found with id ${params.id}"
            redirect(action: edit, id: params.id)
        }
    }

    def save = {
        def link = new BlogLink()
        link.properties = params
        if(link.save()) {
            flash.message = "Link ${link.id} created."
            redirect(action:show,id:link.id)
        }
        else {
            render(view:'create',model:[link:link, 'articles': BlogArticle.findAll(
                        [sort: 'dateCreated',
                            order: 'desc'])])
        }
    }


}
