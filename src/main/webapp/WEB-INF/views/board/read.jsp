<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp"%>

<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Read</h1>
<p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
    For more information about DataTables, please visit the <a target="_blank"
                                                               href="https://datatables.net">official DataTables documentation</a>.</p>

<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Board Read</h6>
    </div>
    <div class="card-body">
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Bno</span>
            </div>
            <input type="text" class="form-control" value="<c:out value="${vo.bno}"/>"  readonly>
        </div>
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Title</span>
            </div>
            <input type="text" name="title" class="form-control"  value="<c:out value="${vo.title}"/>"  readonly >
        </div>
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Content</span>
            </div>
            <textarea name="content" class="form-control" readonly rows="3"><c:out value="${vo.content}"/>
            </textarea>
        </div>
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Writer</span>
            </div>
            <input type="text" name="writer" class="form-control"  value="<c:out value="${vo.writer}"/>"  readonly>
        </div>
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">RegDate</span>
            </div>
            <input type="text" name="writer" class="form-control"  value="<c:out value="${vo.regDate}"/>"  readonly>
        </div>
        <div class="float-right mt-2">
            <button type="button" class="btn btn-info btnList" >LIST</button>
            <button type="button" class="btn btn-warning btnModify" >MODIFY</button>
        </div>
    </div>

    <div class="attachList d-flex">
        <c:if test="${vo.attachVOList != null && vo.attachVOList.size() > 0}">
        <c:forEach items="${vo.attachVOList}" var="attach">
            <c:if test="${attach.ano != null}">
                <div>
                    <a href="/files/${attach.fullName}" target="_blank">
                        <img src="/files/s_${attach.fullName}"/>
                    </a>
                </div>
            </c:if>
        </c:forEach>
        </c:if>
    </div>

</div>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <button class="btn btn-info addReplyBtn">Add Reply</button>
    </div>
    <div class="card-body ">
        <div>
            <ul class="list-group replyList">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    Cras justo odio
                    <span class="badge badge-primary badge-pill">14</span>
                </li>
            </ul>
        </div>
        <div class="mt-3" >
            <ul class="pagination">
                <li class="page-item ">
                    <a class="page-link" href="#" tabindex="-1">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item active">
                    <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
                </li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#">Next</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="modal" id="replyModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body ">
                <div class="input-group input-group-lg">
                    <div class="input-group-prepend">
                        <span class="input-group-text">Reply Text</span>
                    </div>
                    <input type="text" name="replyText" class="form-control" >
                </div>
                <div class="input-group input-group-sm">
                    <div class="input-group-prepend">
                        <span class="input-group-text">Replyer</span>
                    </div>
                    <input type="text" name="replyer" class="form-control" >
                </div>
            </div>
            <div class="modal-footer">
                <button id='replyModBtn' type="button" class="btn btn-warning">Modify</button>
                <button id='replyDelBtn' type="button" class="btn btn-danger">Delete</button>
                <button id='replyRegBtn' type="button" class="btn btn-primary">Register</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<form id="actionForm" method="get" action="/board/list">
    <input type="hidden" name="pageNum" value="${cri.pageNum}">
    <input type="hidden" name="amount" value="${cri.amount}">
    <c:if test="${cri.types != null && cri.keyword != null }">
        <c:forEach var="type" items="${cri.types}">
            <input type="hidden" name="types" value="${type}">
        </c:forEach>
        <input type="hidden" name="keyword" value="<c:out value="${cri.keyword}"/>">
    </c:if>
</form>

<%@include file="../includes/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>


<script>

    const actionForm = document.querySelector("#actionForm")
    const bno = '${vo.bno}'

    document.querySelector(".btnList").addEventListener("click",(e) => {
        actionForm.setAttribute("action", "/board/list")
        actionForm.submit()
    }, false)

    document.querySelector(".btnModify").addEventListener("click",(e) => {
        actionForm.setAttribute("action", `/board/modify/\${bno}`)
        actionForm.submit()
    }, false)
</script>

<script>

    const boardBno = ${vo.bno}
    const replyUL = document.querySelector(".replyList")
    const pageUL = document.querySelector(".pagination")

    const getList = async (pageParam, amountParam)  => {

        const pageNum = pageParam || 1
        const amount = amountParam || 10

        const res = await axios.get(`/reply/list/\${boardBno}`, {
            params: {pageNum, amount}
        })
        const data = res.data
        const pageDTO = data.pageDTO
        const replyList = data.replyList

        printReplyList(pageDTO, replyList)

    }

    const registerReply = async (replyObj) => {

        const res = await axios.post('/reply/register', replyObj )

        const data = res.data

        const lastPage = Math.ceil(data.COUNT / 10.0)

        getList(lastPage)


    }

    const printReplyList = (pageDTO, replyList) => {

        replyUL.innerHTML = ""

        let str = ''
        for (const reply of replyList) {

            const {rno, replyText, replyer} = reply

            str += `
              <li data-rno="\${rno}" class="list-group-item d-flex justify-content-between align-items-center">
                \${rno} --- \${replyText}
                <span class="badge badge-primary badge-pill">\${replyer}</span>
              </li>`
        }
        replyUL.innerHTML = str

        //----------------------------------------
        const {startPage, endPage, prev, next} = pageDTO
        const pageNum = pageDTO.cri.pageNum

        let pageStr = ''

        //prev
        if(prev){
            pageStr +=
                `<li class="page-item ">
                    <a class="page-link" href="\${startPage-1}" tabindex="-1">Previous</a>
                </li>`
        }

        for (let i = startPage; i <= endPage ; i++) {

            pageStr += `<li class="page-item \${i == pageNum? 'active': ''}">
        <a class="page-link" href="\${i}">\${i}</a>
    </li>`
        }

        //next
        if(next){
            pageStr +=
                `<li class="page-item ">
                    <a class="page-link" href="\${endPage + 1 }" tabindex="-1">Next</a>
                </li>`
        }

        pageUL.innerHTML = pageStr

    }

    pageUL.addEventListener("click", (e) => {
        e.preventDefault()
        e.stopPropagation()
        const target = e.target
        const pageNum = target.getAttribute("href")
        console.log(pageNum)
        currentPage = pageNum
        getList(pageNum)
    }, false)

    //현재 댓글 페이지
    let currentPage = 1
    let currentRno = 0

    replyUL.addEventListener("click", e => {
        e.stopPropagation()
        const target = e.target
        currentRno = target.getAttribute("data-rno")

        getReply(currentRno).then( result => {
            replyTextInput.value = result.replyText
            replyerInput.value = result.replyer
            replyAddModal.show()
        } )

    } , false)

    getList()


    const replyAddModal = new bootstrap.Modal(document.querySelector('#replyModal'))
    const replyTextInput = document.querySelector("input[name='replyText']")
    const replyerInput = document.querySelector("input[name='replyer']")

    const getReply = async (rno) => {

        const res = await axios.get(`/reply/\${rno}`)

        return res.data
    }

    const deleteReply = async (rno) => {

        const res = await axios.delete(`/reply/\${rno}`)

        return res.data // {Result:true}
    }

    const modifyReply = async  (replyObj) => {

        const res = await  axios.put(`/reply/\${currentRno}`, replyObj)

        return res.data
    }


    //replyAddModal.show()

    document.querySelector("#replyRegBtn").addEventListener("click", e => {
        e.preventDefault()
        e.stopPropagation()

        const replyObj = {
            replyText: replyTextInput.value,
            replyer: replyerInput.value,
            bno: boardBno
        }

        registerReply(replyObj).then( result => {
            replyAddModal.hide()
            replyTextInput.value =''
            replyerInput.value = ''
        })

    }, false)

    document.querySelector("#replyDelBtn").addEventListener("click", e => {

        deleteReply(currentRno).then(result => {
            alert('댓글이 삭제되었습니다')
            replyAddModal.hide()
            getList()
        })
    },false)

    document.querySelector("#replyModBtn").addEventListener("click", e=> {

        const replyObj = {
            replyText: replyTextInput.value,
            replyerInput: replyerInput.value,
            bno: boardBno
        }

        modifyReply(replyObj).then( result => {

            alert("댓글이 수정되었습니다.")
            replyAddModal.hide()
            getList(currentPage)

        })

    },false)

    document.querySelector(".addReplyBtn").addEventListener("click", e => {

        replyAddModal.show()

    }, false)


</script>

<%@include file="../includes/end.jsp"%>