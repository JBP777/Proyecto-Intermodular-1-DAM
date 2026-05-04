// ============================================================
// Ruta base al PHP
// ============================================================
const API_URL = 'api_incidencias.php';

// ============================================================
// TRADUCCIONES — todo el texto del HTML
// ============================================================
const traducciones = {
    es: {
        // Navbar
        nav: ['Inicio', 'Quiénes somos', 'Categorías', 'Ranking', 'App'],
        btnIdioma: '🌐 EN',
        btnContacto: 'Contacto',

        // Hero
        heroTitle: 'Encuentra técnicos,\n            soluciona problemas,\n            gana recompensas',
        verCategorias: 'Ver categorías',

        // Nosotros
        nosotrosTitle: '¿Qué es Incidencias App?',
        nosotrosIntro: 'Un proyecto creado por Khaled, Thiago y Jesús para facilitar la ayuda técnica y comunitaria de forma rápida y organizada.',
        cards: [
            { h3: 'Zonas',      p: 'Las incidencias se organizan por zonas para llegar a quien realmente puede ayudar.' },
            { h3: 'Técnicos',   p: 'Colaboradores especializados en distintas categorías.' },
            { h3: 'Reputación', p: 'Gana valoraciones, logros y recompensas por ayudar.' }
        ],

        // Categorías
        categoriasTitle: 'Categorías populares',
        catCards: [
            { h3: 'Infraestructura', p: 'Baches, alumbrado, mobiliario urbano.' },
            { h3: 'Tecnología',      p: 'Redes, ordenadores y soporte técnico.' },
            { h3: 'Limpieza',        p: 'Parques, calles y espacios públicos.' },
            { h3: 'Seguridad',       p: 'Alertas y avisos preventivos.' }
        ],

        // Ranking
        rankingTitle: '🏆 Ranking de Colaboradores',
        rankingIntro: 'Los técnicos más destacados de nuestra comunidad',
        statLabels: ['Incidencias totales', 'Colaboradores activos', 'Problemas resueltos'],
        rankHeader: ['Posición', 'Usuario', 'Email', 'Resueltas'],
        cargando: 'Cargando ranking...',
        sinDatos: 'No hay datos disponibles',
        errorRanking: '❌ Error al cargar el ranking.',

        // Descarga
        descargaTitle: 'Instala FIXIT hoy mismo dandole aqui!',
        descargaSubtitle: 'Disponible para ordenadores de sobremesa y para portatiles.',
        descargaBtn: 'Descarga',

        // Contacto
        contactoTitle: '📧 Contacto',
        contactoSubtitle: '¿Tienes alguna pregunta? Escríbenos',
        labels: ['Nombre', 'Correo electrónico', 'Asunto', 'Mensaje'],
        placeholders: ['Tu nombre completo', 'tu@email.com', '¿En qué podemos ayudarte?', 'Cuéntanos más detalles...'],
        btnEnviar: 'Enviar mensaje',
        successMsg: '¡Mensaje enviado con éxito!',
        successSub: 'Te responderemos pronto',

        // Footer
        footer: ['© 2025 · IES Pere Maria Orts · CFGS DAM', 'Desarrollado por Khaled, Thiago y Jesús']
    },
    en: {
        // Navbar
        nav: ['Home', 'About us', 'Categories', 'Ranking', 'App'],
        btnIdioma: '🌐 ES',
        btnContacto: 'Contact',

        // Hero
        heroTitle: 'Find technicians,\n            solve problems,\n            earn rewards',
        verCategorias: 'View categories',

        // Nosotros
        nosotrosTitle: 'What is Incidencias App?',
        nosotrosIntro: 'A project created by Khaled, Thiago and Jesús to facilitate fast and organized technical and community support.',
        cards: [
            { h3: 'Zones',       p: 'Issues are organized by zones to reach whoever can really help.' },
            { h3: 'Technicians', p: 'Collaborators specialized in different categories.' },
            { h3: 'Reputation',  p: 'Earn ratings, achievements and rewards for helping.' }
        ],

        // Categorías
        categoriasTitle: 'Popular categories',
        catCards: [
            { h3: 'Infrastructure', p: 'Potholes, lighting, street furniture.' },
            { h3: 'Technology',     p: 'Networks, computers and technical support.' },
            { h3: 'Cleaning',       p: 'Parks, streets and public spaces.' },
            { h3: 'Security',       p: 'Alerts and preventive notices.' }
        ],

        // Ranking
        rankingTitle: '🏆 Collaborator Ranking',
        rankingIntro: 'The most outstanding technicians in our community',
        statLabels: ['Total incidents', 'Active collaborators', 'Problems solved'],
        rankHeader: ['Position', 'User', 'Email', 'Solved'],
        cargando: 'Loading ranking...',
        sinDatos: 'No data available',
        errorRanking: '❌ Error loading ranking.',

        // Descarga
        descargaTitle: 'Install FIXIT today by clicking here!',
        descargaSubtitle: 'Available for desktop computers and laptops.',
        descargaBtn: 'Download',

        // Contacto
        contactoTitle: '📧 Contact',
        contactoSubtitle: 'Do you have a question? Write to us',
        labels: ['Name', 'Email', 'Subject', 'Message'],
        placeholders: ['Your full name', 'your@email.com', 'How can we help you?', 'Tell us more details...'],
        btnEnviar: 'Send message',
        successMsg: 'Message sent successfully!',
        successSub: 'We will get back to you soon',

        // Footer
        footer: ['© 2025 · IES Pere Maria Orts · CFGS DAM', 'Developed by Khaled, Thiago and Jesús']
    }
};

let idiomaActual = 'es';

// ============================================================
// CAMBIAR IDIOMA
// ============================================================
function cambiarIdioma() {
    idiomaActual = idiomaActual === 'es' ? 'en' : 'es';
    const t = traducciones[idiomaActual];

    // Navbar links
    const navLinks = document.querySelectorAll('.nav-links a');
    navLinks.forEach((a, i) => { if (t.nav[i]) a.textContent = t.nav[i]; });

    // Botones navbar (primero = idioma, segundo = contacto)
    const botonesNav = document.querySelectorAll('.btn-nav');
    if (botonesNav[0]) botonesNav[0].textContent = t.btnIdioma;
    if (botonesNav[1]) botonesNav[1].textContent = t.btnContacto;

    // Hero
    const heroH1 = document.querySelector('.hero h1');
    if (heroH1) heroH1.textContent = t.heroTitle;
    const btnGhost = document.querySelector('.btn-ghost');
    if (btnGhost) btnGhost.textContent = t.verCategorias;

    // Nosotros
    setText('.about h2', t.nosotrosTitle);
    setText('.about-intro', t.nosotrosIntro);
    document.querySelectorAll('.about-card').forEach((card, i) => {
        if (t.cards[i]) {
            setText(card, 'h3', t.cards[i].h3);
            setText(card, 'p',  t.cards[i].p);
        }
    });

    // Categorías
    setText('.categories h2', t.categoriasTitle);
    document.querySelectorAll('.category-card').forEach((card, i) => {
        if (t.catCards[i]) {
            setText(card, 'h3', t.catCards[i].h3);
            setText(card, 'p',  t.catCards[i].p);
        }
    });

    // Ranking
    setText('.ranking-section h2', t.rankingTitle);
    setText('.ranking-intro', t.rankingIntro);
    document.querySelectorAll('.stat-label').forEach((el, i) => {
        if (t.statLabels[i]) el.textContent = t.statLabels[i];
    });
    const rankHeaderCols = document.querySelectorAll('.ranking-header div');
    rankHeaderCols.forEach((el, i) => { if (t.rankHeader[i]) el.textContent = t.rankHeader[i]; });

    // Descarga
    setText('.download-box h2', t.descargaTitle);
    setText('.download-box p', t.descargaSubtitle);
    const dlBtn = document.querySelector('.download-buttons button');
    if (dlBtn) dlBtn.textContent = t.descargaBtn;

    // Contacto
    setText('.form-contacto h2', t.contactoTitle);
    setText('.form-subtitle', t.contactoSubtitle);

    // Labels del formulario
    const labels = document.querySelectorAll('.form-group label');
    labels.forEach((el, i) => { if (t.labels[i]) el.textContent = t.labels[i]; });

    // Placeholders
    const inputs = [
        document.getElementById('nombre'),
        document.getElementById('email'),
        document.getElementById('asunto'),
        document.getElementById('mensaje')
    ];
    inputs.forEach((el, i) => { if (el && t.placeholders[i]) el.placeholder = t.placeholders[i]; });

    // Botón enviar
    const btnText = document.querySelector('.btn-text');
    if (btnText) btnText.textContent = t.btnEnviar;

    // Mensaje éxito
    setText('.success-message p', t.successMsg);
    setText('.success-message small', t.successSub);

    // Footer
    const footerPs = document.querySelectorAll('.footer p');
    footerPs.forEach((el, i) => { if (t.footer[i]) el.textContent = t.footer[i]; });
}

// Helper para setText con selector global o dentro de un elemento padre
function setText(parentOrSelector, selectorOrText, text) {
    if (text === undefined) {
        // Llamada con (selector, texto)
        const el = document.querySelector(parentOrSelector);
        if (el) el.textContent = selectorOrText;
    } else {
        // Llamada con (elemento, selector, texto)
        const el = parentOrSelector.querySelector(selectorOrText);
        if (el) el.textContent = text;
    }
}

// ============================================================
// RANKING
// ============================================================
async function cargarRanking() {
    const rankingList = document.getElementById('rankingList');
    const t = traducciones[idiomaActual];
    rankingList.innerHTML = `<div class="loading">${t.cargando}</div>`;

    try {
        const response = await fetch(`${API_URL}?accion=ranking`);
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        const data = await response.json();
        if (data.error) throw new Error(data.error);

        rankingList.innerHTML = '';

        if (!data.length) {
            rankingList.innerHTML = `<div class="no-data">${t.sinDatos}</div>`;
            return;
        }

        data.forEach((usuario, index) => {
            const rankingItem = document.createElement('div');
            rankingItem.className = 'ranking-row';
            if (index < 3) rankingItem.classList.add(`top-${index + 1}`);

            const medals = ['🥇', '🥈', '🥉'];
            const medal = medals[index] ?? `#${index + 1}`;

            rankingItem.innerHTML = `
                <div class="rank-col">${medal}</div>
                <div class="user-col">${usuario.usuario}</div>
                <div class="email-col">${usuario.email}</div>
                <div class="solved-col"><span class="badge">${usuario.incidenciasresueltas}</span></div>
            `;
            rankingList.appendChild(rankingItem);
        });

    } catch (error) {
        console.error('Error al cargar el ranking:', error);
        rankingList.innerHTML = `
            <div class="error-message">
                ${t.errorRanking}<br>
                <small>Comprueba que XAMPP está en marcha y que <code>api_incidencias.php</code> está en la misma carpeta.</small>
            </div>`;
    }
}

// ============================================================
// ESTADÍSTICAS
// ============================================================
async function cargarEstadisticas() {
    try {
        const response = await fetch(`${API_URL}?accion=stats`);
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        const stats = await response.json();
        if (stats.error) throw new Error(stats.error);

        animarNumero('totalIncidencias',    stats.totalincidencias);
        animarNumero('totalColaboradores',  stats.totalcolaboradores);
        animarNumero('incidenciasCerradas', stats.incidenciascerradas);

    } catch (error) {
        console.error('Error al cargar estadísticas:', error);
        ['totalIncidencias', 'totalColaboradores', 'incidenciasCerradas'].forEach(id => {
            const el = document.getElementById(id);
            if (el) el.textContent = '—';
        });
    }
}

// ============================================================
// Animación de conteo
// ============================================================
function animarNumero(elementId, valorFinal, duracion = 1000) {
    const el = document.getElementById(elementId);
    if (!el) return;
    const pasos = 40;
    const incremento = valorFinal / pasos;
    const intervalo = duracion / pasos;
    let valorActual = 0;
    const timer = setInterval(() => {
        valorActual += incremento;
        if (valorActual >= valorFinal) {
            el.textContent = valorFinal;
            clearInterval(timer);
        } else {
            el.textContent = Math.floor(valorActual);
        }
    }, intervalo);
}

// ============================================================
// Formulario de contacto
// ============================================================
function handleSubmit(event) {
    event.preventDefault();
    const form           = document.getElementById('contactForm');
    const successMessage = document.getElementById('successMessage');
    form.style.opacity = '0.5';
    setTimeout(() => {
        form.reset();
        form.style.display         = 'none';
        successMessage.style.display = 'block';
        setTimeout(() => {
            form.style.display         = 'block';
            form.style.opacity         = '1';
            successMessage.style.display = 'none';
        }, 3000);
    }, 500);
}

// ============================================================
// Scroll suave al contacto
// ============================================================
function scrollToContact() {
    document.getElementById('contacto').scrollIntoView({ behavior: 'smooth' });
}

// ============================================================
// Inicialización
// ============================================================
window.addEventListener('DOMContentLoaded', () => {
    cargarRanking();
    cargarEstadisticas();
});

setInterval(cargarRanking,      30_000);
setInterval(cargarEstadisticas, 30_000);